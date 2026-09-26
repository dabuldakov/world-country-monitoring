package org.wcm.usecase

import org.springframework.stereotype.Component
import org.wcm.domain.api.CountryAdapter
import org.wcm.domain.api.RefillStatusAdapter
import org.wcm.domain.api.RefreshJobAdapter
import org.wcm.domain.model.RefillCountryStatus
import org.wcm.domain.model.RefillFeature
import org.wcm.domain.model.RefillFeatureStatus
import org.wcm.domain.model.RefreshJob
import org.wcm.domain.model.RefreshJobItem
import org.wcm.usecase.api.RefillApi
import org.wcm.usecase.api.RefreshJobApi
import java.time.Instant

@Component
class RefreshJobUseCase(
    private val refreshJobAdapter: RefreshJobAdapter,
    private val refillApi: RefillApi,
    private val countryAdapter: CountryAdapter,
    private val refillStatusAdapter: RefillStatusAdapter
) : RefreshJobApi {

    override fun enqueue(feature: RefillFeature, countryCode: String?): RefreshJob {
        val normalizedCountry = countryCode?.trim()?.uppercase()?.takeIf { it.isNotEmpty() }
        refreshJobAdapter.findActiveJob(feature.key, normalizedCountry)?.let { return it }

        val countries = normalizedCountry?.let { listOf(it) } ?: countryCodes()
        return createJob(feature, countries)
    }

    override fun retryFailed(jobId: Long): RefreshJob? {
        val job = refreshJobAdapter.findJobById(jobId) ?: return null
        val failedCountries = refreshJobAdapter.findItems(jobId)
            .filter { it.status == "FAILED" }
            .map { it.countryCode }

        if (failedCountries.isEmpty()) {
            return job
        }

        val feature = RefillFeature.fromKey(job.feature) ?: return job
        return createJob(feature, failedCountries)
    }

    override fun get(jobId: Long): RefreshJob? = refreshJobAdapter.findJobById(jobId)

    override fun list(feature: RefillFeature?, status: String?): List<RefreshJob> =
        refreshJobAdapter.findJobs(feature?.key, status?.uppercase())

    override fun countryStatuses(feature: RefillFeature?): List<RefillCountryStatus> =
        refreshJobAdapter.findCountryStatuses(feature?.key)

    override fun processNextJob(): Boolean {
        val job = refreshJobAdapter.claimNextQueuedJob() ?: return false
        process(job)
        return true
    }

    private fun createJob(feature: RefillFeature, countries: List<String>): RefreshJob {
        val job = refreshJobAdapter.saveJob(
            RefreshJob(
                feature = feature.key,
                countryCode = countries.singleOrNull(),
                status = "QUEUED",
                total = countries.size,
                createdAt = Instant.now()
            )
        )

        refreshJobAdapter.saveItems(
            countries.map { country ->
                RefreshJobItem(
                    jobId = job.id!!,
                    countryCode = country,
                    status = "PENDING"
                )
            }
        )

        return job
    }

    private fun process(job: RefreshJob) {
        val feature = RefillFeature.fromKey(job.feature)
        if (feature == null) {
            refreshJobAdapter.saveJob(
                job.copy(
                    status = "FAILED",
                    errorMessage = "Unknown feature ${job.feature}",
                    finishedAt = Instant.now()
                )
            )
            return
        }

        val items = refreshJobAdapter.findItems(job.id!!).filter { it.status == "PENDING" }
        var processed = job.processed
        var failed = job.failed

        items.forEach { item ->
            try {
                refillApi.forCountry(feature, item.countryCode)
                refreshJobAdapter.updateItem(
                    item.copy(status = "SUCCESS", updatedAt = Instant.now())
                )
                refreshJobAdapter.saveCountryStatuses(
                    listOf(
                        RefillCountryStatus(
                            feature = job.feature,
                            countryCode = item.countryCode,
                            lastUpdatedAtEpochMillis = System.currentTimeMillis(),
                            status = "SUCCESS"
                        )
                    )
                )
            } catch (exception: Exception) {
                failed += 1
                refreshJobAdapter.updateItem(
                    item.copy(
                        status = "FAILED",
                        errorMessage = exception.message,
                        updatedAt = Instant.now()
                    )
                )
                refreshJobAdapter.saveCountryStatuses(
                    listOf(
                        RefillCountryStatus(
                            feature = job.feature,
                            countryCode = item.countryCode,
                            lastUpdatedAtEpochMillis = null,
                            status = "FAILED",
                            errorMessage = exception.message
                        )
                    )
                )
            }

            processed += 1
            refreshJobAdapter.saveJob(job.copy(processed = processed, failed = failed))
        }

        val status = when {
            failed == 0 -> "SUCCESS"
            processed == 0 -> "FAILED"
            else -> "PARTIAL"
        }
        val finishedAt = Instant.now()

        refreshJobAdapter.saveJob(
            job.copy(
                status = status,
                processed = processed,
                failed = failed,
                finishedAt = finishedAt
            )
        )

        refillStatusAdapter.saveAll(
            listOf(
                RefillFeatureStatus(
                    feature = job.feature,
                    lastUpdatedAtEpochMillis = finishedAt.toEpochMilli(),
                    status = status,
                    processedCount = processed,
                    errorMessage = if (failed > 0) "$failed country(ies) failed" else null
                )
            )
        )
    }

    private fun countryCodes(): List<String> =
        runCatching { countryAdapter.getAll().map { it.code } }.getOrDefault(emptyList())
}