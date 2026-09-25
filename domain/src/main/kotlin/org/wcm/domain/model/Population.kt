package org.wcm.domain.model

import java.time.LocalDate

data class Population(
    val id: Long? = null,
    val population: Double? = null,
    val countryCode: String,
    val date: LocalDate
)
