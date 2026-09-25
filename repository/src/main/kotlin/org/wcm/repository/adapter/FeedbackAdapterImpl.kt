package org.wcm.repository.adapter

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.wcm.domain.api.FeedbackAdapter
import org.wcm.domain.model.Feedback
import org.wcm.repository.FeedbackRepository
import org.wcm.repository.mapper.FeedbackMapper

@Component
class FeedbackAdapterImpl(
    private val repository: FeedbackRepository,
    private val mapper: FeedbackMapper
) : FeedbackAdapter {

    @Transactional
    override fun save(feedback: Feedback): Feedback =
        mapper.toDomain(repository.save(mapper.toEntity(feedback)))

    @Transactional(readOnly = true)
    override fun findAll(): List<Feedback> =
        repository.findAllByOrderByCreatedAtDesc().map { mapper.toDomain(it) }
}
