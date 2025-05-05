package org.wcm.domain.model

import java.time.LocalDate

data class GrossDomesticProduct(
    val id: Long? = null,
    val absolut: Double? = null,
    val purchasingPowerParities: Double? = null,
    val countryCode: String,
    val date: LocalDate
)
