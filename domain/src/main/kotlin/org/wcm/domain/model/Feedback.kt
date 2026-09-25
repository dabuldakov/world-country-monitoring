package org.wcm.domain.model

import java.time.LocalDateTime

data class Feedback(
    val id: Long? = null,
    val email: String,
    val message: String,
    val createdAt: LocalDateTime? = null
)
