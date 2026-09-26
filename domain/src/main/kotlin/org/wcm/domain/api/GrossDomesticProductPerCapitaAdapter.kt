package org.wcm.domain.api

import org.wcm.domain.model.GrossDomesticProductPerCapita
import java.time.LocalDate

interface GrossDomesticProductPerCapitaAdapter {

    fun getByCountryCode(countryCode: String): List<GrossDomesticProductPerCapita>
    fun getAllCountriesByYear(data: LocalDate): List<GrossDomesticProductPerCapita>
    fun saveAll(items: List<GrossDomesticProductPerCapita>)
}