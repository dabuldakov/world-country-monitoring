package org.wcm.repository.adapter

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.wcm.domain.api.RefreshJobAdapter
import org.wcm.domain.model.RefillCountryStatus
import org.wcm.domain.model.RefreshJob
import org.wcm.domain.model.RefreshJobItem
import org.wcm.repository.RefillCountryStatusRepository
import org.wcm.repository.RefreshJobItemRepository
import org.wcm.repository.RefreshJobRepository
import org.wcm.repository.mapper.RefreshJobMapper
import java.time.Instant

@Component
class RefreshJobAdapterImpl(
    private val jobRepository: RefreshJobRepository,
    private val itemRepository: RefreshJobItemRepository,
    private val countryStatusRepository: RefillCountryStatusRepository,
    private val mapper: RefreshJobMapper
) : RefreshJobAdapter {

    @Transactional
    override fun saveJob(job: RefreshJob): RefreshJob =
        mapper.toDomain(jobRepository.save(mapper.toEntity(job)))

    @Transactional(readOnly = true)
    override fun findJobById(id: Long): RefreshJob? =
        jobRepository.findById(id).map { mapper.toDomain(it) }.orElse(null)

    @Transactional(readOnly = true)
    override fun findActiveJob(feature: String, countryCode: String?): RefreshJob? {
        val activeStatuses = ACTIVE_STATUSES
        val entity = if (countryCode == null) {
            jobRepository.findFirstByFeatureAndCountryCodeIsNullAndStatusIn(feature, activeStatuses)
        } else {
            jobRepository.findFirstByFeatureAndCountryCodeAndStatusIn(feature, countryCode, activeStatuses)
        }
        return entity?.let { mapper.toDomain(it) }
    }

    @Transactional(readOnly = true)
    override fun findJobs(feature: String?, status: String?): List<RefreshJob> =
        jobRepository.findAllByOrderByIdDesc()
            .filter { feature == null || it.feature == feature }
            .filter { status == null || it.status == status }
            .map { mapper.toDomain(it) }

    @Transactional
    override fun claimNextQueuedJob(): RefreshJob? {
        val entity = jobRepository.claimNextQueued() ?: return null
        val running = entity.copy(status = "RUNNING", startedAt = Instant.now())
        return mapper.toDomain(jobRepository.save(running))
    }

    @Transactional
    override fun saveItems(items: List<RefreshJobItem>) {
        items.forEach { itemRepository.save(mapper.toEntity(it)) }
    }

    @Transactional(readOnly = true)
    override fun findItems(jobId: Long): List<RefreshJobItem> =
        itemRepository.findAllByJobIdOrderById(jobId).map { mapper.toDomain(it) }

    @Transactional
    override fun updateItem(item: RefreshJobItem): RefreshJobItem =
        mapper.toDomain(itemRepository.save(mapper.toEntity(item)))

    @Transactional
    override fun saveCountryStatuses(statuses: List<RefillCountryStatus>) {
        statuses.forEach { status ->
            val existing = countryStatusRepository.findFirstByFeatureAndCountryCode(
                status.feature,
                status.countryCode
            )
            countryStatusRepository.save(mapper.toEntity(status, existing))
        }
    }

    @Transactional(readOnly = true)
    override fun findCountryStatuses(feature: String?): List<RefillCountryStatus> {
        val entities = if (feature == null) {
            countryStatusRepository.findAll()
        } else {
            countryStatusRepository.findAllByFeature(feature)
        }
        return entities.map { mapper.toDomain(it) }
    }

    private companion object {
        val ACTIVE_STATUSES = listOf("QUEUED", "RUNNING")
    }
}