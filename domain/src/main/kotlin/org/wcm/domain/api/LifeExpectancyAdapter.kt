package org.wcm.domain.api

import org.wcm.domain.model.LifeExpectancy
import java.time.LocalDate

interface LifeExpectancyAdapter {

    fun getByCountryCode(countryCode: String): List<LifeExpectancy>
    fun getAllCountriesByYear(data: LocalDate): List<LifeExpectancy>
    fun saveAll(items: List<LifeExpectancy>)
}