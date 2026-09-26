package org.wcm.domain.api

import org.wcm.domain.model.Debt
import org.wcm.domain.model.GrossDomesticProduct
import org.wcm.domain.model.GrossDomesticProductPerCapita
import org.wcm.domain.model.InternationalReserve
import org.wcm.domain.model.LifeExpectancy
import org.wcm.domain.model.Population

interface WorldBankApi {

    fun getAllHistoryGDPbyCountry(countryCode: String): List<GrossDomesticProduct>
    fun getAllHistoryGDPPerCapitaByCountry(countryCode: String): List<GrossDomesticProductPerCapita>
    fun getAllHistoryPercentageToGDPByCountry(countryCode: String): List<Debt>
    fun getAllHistoryDebtAmountByCountry(countryCode: String): List<Debt>
    fun getAllHistoryReservesAmountByCountry(countryCode: String): List<InternationalReserve>
    fun getAllHistoryPopulationByCountry(countryCode: String): List<Population>
    fun getAllHistoryLifeExpectancyByCountry(countryCode: String): List<LifeExpectancy>
}
