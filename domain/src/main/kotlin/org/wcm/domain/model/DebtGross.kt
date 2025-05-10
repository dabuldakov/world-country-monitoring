package org.wcm.domain.model

import java.time.LocalDate

data class DebtGross(
    val ratioPercentage: Double,
    val date: LocalDate
)
