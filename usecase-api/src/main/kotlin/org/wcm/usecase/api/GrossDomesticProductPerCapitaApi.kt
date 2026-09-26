package org.wcm.usecase.api

import org.wcm.domain.model.GrossDomesticProductPerCapita
import java.time.LocalDate

interface GrossDomesticProductPerCapitaApi {

    fun getByCountryCode(countryCode: String): List<GrossDomesticProductPerCapita>
    fun getAllCountriesByYear(date: LocalDate): List<GrossDomesticProductPerCapita>
}