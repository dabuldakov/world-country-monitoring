package org.wcm.usecase.api

import org.wcm.domain.model.GrossDomesticProduct
import java.time.LocalDate

interface GrossDomesticProductApi {

    fun findAllByCountryCode(countryCode: String): List<GrossDomesticProduct>
    fun findAllCountriesByDate(date: LocalDate): List<GrossDomesticProduct>
}