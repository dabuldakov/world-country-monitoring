package org.wcm.repository.adapter

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.wcm.domain.api.VisitAdapter
import org.wcm.domain.model.VisitStatistics
import org.wcm.repository.VisitCounterRepository
import org.wcm.repository.mapper.VisitMapper

@Component
class VisitAdapterImpl(
    private val repository: VisitCounterRepository,
    private val mapper: VisitMapper
) : VisitAdapter {

    @Transactional
    override fun registerVisit(): VisitStatistics {
        repository.incrementTotal()
        return mapper.toDomain(repository.findById(VISIT_COUNTER_ID).orElseThrow())
    }

    @Transactional(readOnly = true)
    override fun getStatistics(): VisitStatistics =
        mapper.toDomain(repository.findById(VISIT_COUNTER_ID).orElseThrow())

    private companion object {
        const val VISIT_COUNTER_ID = 1L
    }
}
