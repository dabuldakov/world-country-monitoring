package org.wcm.domain.model

data class RefillCountryStatus(
    val feature: String,
    val countryCode: String,
    val lastUpdatedAtEpochMillis: Long? = null,
    val status: String? = null,
    val errorMessage: String? = null
)