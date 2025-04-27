package entity

import java.util.Date

data class InternationalReserve(
    val id: Long? = null,
    val amount: Long,
    val foreignExchange: Long,
    val monetaryGold: Long,
    val date: Date
)
