package org.wcm.domain.model

data class RefillFeatureStatus(
    val feature: String,
    val lastUpdatedAtEpochMillis: Long? = null,
    val status: String? = null,
    val processedCount: Int? = null,
    val errorMessage: String? = null
)