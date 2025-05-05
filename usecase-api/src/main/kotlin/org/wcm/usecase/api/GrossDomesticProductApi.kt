package org.wcm.usecase.api

import org.wcm.domain.model.GrossDomesticProduct

interface GrossDomesticProductApi {

    fun getByCountryCode(countryCode: String): List<GrossDomesticProduct>
}