package org.wcm.domain.api

import org.wcm.domain.model.Debt
import org.wcm.domain.model.GrossDomesticProduct

interface WorldBankApi {

    fun getAllHistoryGDPbyCountry(countryCode: String): List<GrossDomesticProduct>
    fun getAllHistoryPercentageToGDPByCountry(countryCode: String): List<Debt>
}