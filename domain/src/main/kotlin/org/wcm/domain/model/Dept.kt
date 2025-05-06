package org.wcm.domain.model

import java.time.LocalDate

data class Dept(
    val id: Long? = null,
    val foreign: Double? = null,
    val countryCode: String,
    val date: LocalDate
)
