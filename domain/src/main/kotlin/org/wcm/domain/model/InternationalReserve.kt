package org.wcm.domain.model

import java.util.*

data class InternationalReserve(
    val id: Long?,
    val amount: Long,
    val foreignExchange: Long,
    val monetaryGold: Long,
    val countryCode: String,
    val date: Date
)
