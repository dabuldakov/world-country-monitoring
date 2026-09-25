package org.wcm.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.wcm.repository.entity.FeedbackEntity

@Repository
interface FeedbackRepository : JpaRepository<FeedbackEntity, Long> {

    fun findAllByOrderByCreatedAtDesc(): List<FeedbackEntity>
}
