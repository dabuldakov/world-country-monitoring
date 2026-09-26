package org.wcm.repository.adapter

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.wcm.domain.api.RefillStatusAdapter
import org.wcm.domain.model.RefillFeatureStatus
import org.wcm.repository.DataRefreshStatusRepository
import org.wcm.repository.mapper.DataRefreshStatusMapper

@Component
class RefillStatusAdapterImpl(
    private val repository: DataRefreshStatusRepository,
    private val mapper: DataRefreshStatusMapper
) : RefillStatusAdapter {

    @Transactional
    override fun saveAll(statuses: List<RefillFeatureStatus>) {
        statuses.forEach { status ->
            repository.save(mapper.toEntity(status))
        }
    }

    @Transactional(readOnly = true)
    override fun getAll(): List<RefillFeatureStatus> =
        repository.findAll().map { mapper.toDomain(it) }
}