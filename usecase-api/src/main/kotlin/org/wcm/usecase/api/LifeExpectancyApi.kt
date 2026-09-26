package org.wcm.usecase.api

import org.wcm.domain.model.LifeExpectancy
import java.time.LocalDate

interface LifeExpectancyApi {

    fun getByCountryCode(countryCode: String): List<LifeExpectancy>
    fun getAllCountriesByYear(date: LocalDate): List<LifeExpectancy>
}