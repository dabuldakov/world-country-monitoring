package org.wcm.domain.model

import java.time.LocalDate

data class MoneySupply(
    val id: Long? = null,
    val amountLocalCurrency: Double? = null,
    val amountUsd: Double? = null,
    val countryCode: String,
    val date: LocalDate
)
