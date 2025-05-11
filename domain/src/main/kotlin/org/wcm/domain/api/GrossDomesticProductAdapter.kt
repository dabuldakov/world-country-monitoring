package org.wcm.domain.api

import org.wcm.domain.model.GrossDomesticProduct
import java.time.LocalDate

interface GrossDomesticProductAdapter {

    fun getByCountryCode(countryCode: String): List<GrossDomesticProduct>

    fun saveAll(gDPs: List<GrossDomesticProduct>)

    fun findAllCountriesByDate(date: LocalDate): List<GrossDomesticProduct>
}