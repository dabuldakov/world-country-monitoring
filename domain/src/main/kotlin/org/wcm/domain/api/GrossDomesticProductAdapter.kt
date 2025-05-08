package org.wcm.domain.api

import org.wcm.domain.model.GrossDomesticProduct

interface GrossDomesticProductAdapter {

    fun getByCountryCode(countryCode: String): List<GrossDomesticProduct>

    fun saveAll(gDPs: List<GrossDomesticProduct>)
}