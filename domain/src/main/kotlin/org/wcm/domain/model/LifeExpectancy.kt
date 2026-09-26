package org.wcm.domain.model

import java.time.LocalDate

data class LifeExpectancy(
    val id: Long? = null,
    val years: Double? = null,
    val countryCode: String,
    val date: LocalDate
)