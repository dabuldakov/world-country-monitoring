package org.wcm.domain.model

import java.time.Instant

data class RefreshJobItem(
    val id: Long? = null,
    val jobId: Long,
    val countryCode: String,
    val status: String,
    val errorMessage: String? = null,
    val updatedAt: Instant? = null
)