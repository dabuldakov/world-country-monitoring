package org.wcm.usecase

import org.springframework.stereotype.Component
import org.wcm.domain.api.CountryAdapter
import org.wcm.domain.api.GrossDomesticProductAdapter
import org.wcm.domain.api.WorldBankApi
import org.wcm.usecase.api.RefillApi

@Component
class RefillUseCase(
    private val worldBankApi: WorldBankApi,
    private val countryAdapter: CountryAdapter,
    private val gDPAdapter: GrossDomesticProductAdapter
) : RefillApi {

    override fun forAllCountriesGDP() {
        countryAdapter.getAll().forEach { country ->
            worldBankApi.getAllHistoryGDPbyCountry(country.code)
             .let { gdp -> gDPAdapter.saveAll(gdp) }
        }
    }

    override fun forCountryGDP(countryCode: String) {
        worldBankApi.getAllHistoryGDPbyCountry(countryCode)
            .let { gdp -> gDPAdapter.saveAll(gdp) }
    }
}