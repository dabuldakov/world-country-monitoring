package org.wcm.domain.api

import org.wcm.domain.model.Population
import java.time.LocalDate

interface PopulationAdapter {

    fun getByCountryCode(countryCode: String): List<Population>
    fun getAllCountriesByDate(date: LocalDate): List<Population>
    fun saveAll(populations: List<Population>)
}
