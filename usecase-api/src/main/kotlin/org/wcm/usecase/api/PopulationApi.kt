package org.wcm.usecase.api

import org.wcm.domain.model.Population
import java.time.LocalDate

interface PopulationApi {

    fun findAllByCountryCode(countryCode: String): List<Population>
    fun findAllCountriesByDate(date: LocalDate): List<Population>
}
