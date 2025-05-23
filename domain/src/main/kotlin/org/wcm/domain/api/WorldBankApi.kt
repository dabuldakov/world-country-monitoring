package org.wcm.domain.api

import org.wcm.domain.model.Debt
import org.wcm.domain.model.GrossDomesticProduct
import org.wcm.domain.model.InternationalReserve

interface WorldBankApi {

    fun getAllHistoryGDPbyCountry(countryCode: String): List<GrossDomesticProduct>
    fun getAllHistoryPercentageToGDPByCountry(countryCode: String): List<Debt>
    fun getAllHistoryReservesAmountByCountry(countryCode: String): List<InternationalReserve>
}