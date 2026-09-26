package org.wcm.domain.model

import java.time.Instant

data class RefreshJob(
    val id: Long? = null,
    val feature: String,
    val countryCode: String? = null,
    val status: String,
    val total: Int = 0,
    val processed: Int = 0,
    val failed: Int = 0,
    val createdAt: Instant? = null,
    val startedAt: Instant? = null,
    val finishedAt: Instant? = null,
    val errorMessage: String? = null
)