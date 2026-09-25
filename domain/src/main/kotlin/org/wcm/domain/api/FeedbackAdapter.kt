package org.wcm.domain.api

import org.wcm.domain.model.Feedback

interface FeedbackAdapter {

    fun save(feedback: Feedback): Feedback

    fun findAll(): List<Feedback>
}
