package org.wcm.domain.model

import java.time.LocalDate

data class GrossDomesticProductPerCapita(
    val id: Long? = null,
    val amount: Double? = null,
    val countryCode: String,
    val date: LocalDate
)