package org.wcm.domain.model

import java.time.LocalDate

data class Debt(
    val id: Long? = null,
    val foreign: Double? = null,
    val percentageToGDP: Double? = null,
    val countryCode: String,
    val date: LocalDate
)
