package org.wcm.domain.model

import java.time.LocalDate

data class InternationalReserve(
    val id: Long? = null,
    val amount: Double? = null,
    val foreignExchange: Double? = null,
    val monetaryGold: Double? = null,
    val monetaryGoldTonn: Double? = null,
    val countryCode: String,
    val date: LocalDate
)
