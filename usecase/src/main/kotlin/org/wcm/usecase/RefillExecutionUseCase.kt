package org.wcm.usecase

import org.springframework.stereotype.Component
import org.wcm.domain.api.CountryAdapter
import org.wcm.domain.api.RefillStatusAdapter
import org.wcm.domain.model.RefillExecutionResult
import org.wcm.domain.model.RefillFeature
import org.wcm.domain.model.RefillFeatureStatus
import org.wcm.usecase.api.RefillApi
import org.wcm.usecase.api.RefillExecutionApi
import java.time.Instant
import java.util.concurrent.atomic.AtomicReference

@Component
class RefillExecutionUseCase(
    private val refillApi: RefillApi,
    private val countryAdapter: CountryAdapter,
    private val refillStatusAdapter: RefillStatusAdapter
) : RefillExecutionApi {

    private val lastResult = AtomicReference<RefillExecutionResult?>(null)

    @Synchronized
    override fun updateAllCountries(): RefillExecutionResult {
        val result = execute("all", countryCount()) { refillApi.forAllCountries() }
        saveStatuses(RefillFeature.entries.toList(), result)
        return result
    }

    @Synchronized
    override fun updateCountry(countryCode: String): RefillExecutionResult {
        val result = execute(countryCode, 1) { refillApi.forCountry(countryCode) }
        saveStatuses(RefillFeature.entries.toList(), result)
        return result
    }

    @Synchronized
    override fun updateFeatureAllCountries(feature: RefillFeature): RefillExecutionResult {
        val result = execute("${feature.key}_all", countryCount()) {
            refillApi.forAllCountries(feature)
        }
        saveStatuses(listOf(feature), result)
        return result
    }

    @Synchronized
    override fun updateFeatureCountry(
        feature: RefillFeature,
        countryCode: String
    ): RefillExecutionResult {
        val result = execute("${feature.key}:$countryCode", 1) {
            refillApi.forCountry(feature, countryCode)
        }
        saveStatuses(listOf(feature), result)
        return result
    }

    override fun lastResult(): RefillExecutionResult? = lastResult.get()

    override fun featureStatuses(): List<RefillFeatureStatus> = refillStatusAdapter.getAll()

    private fun countryCount(): Int = runCatching { countryAdapter.getAll().size }.getOrDefault(0)

    private fun saveStatuses(
        features: List<RefillFeature>,
        result: RefillExecutionResult
    ) {
        refillStatusAdapter.saveAll(
            features.map { feature ->
                RefillFeatureStatus(
                    feature = feature.key,
                    lastUpdatedAtEpochMillis = result.finishedAt.toEpochMilli(),
                    status = result.status,
                    processedCount = result.processedCount,
                    errorMessage = result.errorMessage
                )
            }
        )
    }

    private fun execute(
        operation: String,
        processedCount: Int,
        action: () -> Unit
    ): RefillExecutionResult {
        val startedAt = Instant.now()
        val result = try {
            action()
            RefillExecutionResult(
                operation = operation,
                startedAt = startedAt,
                finishedAt = Instant.now(),
                status = "SUCCESS",
                processedCount = processedCount
            )
        } catch (exception: Exception) {
            RefillExecutionResult(
                operation = operation,
                startedAt = startedAt,
                finishedAt = Instant.now(),
                status = "FAILED",
                processedCount = processedCount,
                errorMessage = exception.message
            )
        }

        lastResult.set(result)
        return result
    }
}