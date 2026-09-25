package org.wcm.usecase.api

import org.wcm.domain.model.Feedback

interface FeedbackApi {

    fun create(email: String, message: String): Feedback

    fun findAll(): List<Feedback>
}
