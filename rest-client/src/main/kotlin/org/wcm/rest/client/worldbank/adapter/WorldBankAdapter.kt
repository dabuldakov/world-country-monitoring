package org.wcm.rest.client.worldbank.adapter

import org.springframework.stereotype.Component
import org.wcm.domain.api.WorldBankApi
import org.wcm.domain.model.Debt
import org.wcm.domain.model.GrossDomesticProduct
import org.wcm.domain.model.InternationalReserve
import org.wcm.domain.model.Population
import org.wcm.rest.client.worldbank.WorldBankClient
import org.wcm.rest.client.worldbank.mapper.WorldBankMapper

@Component
class WorldBankAdapter(
    private val client: WorldBankClient,
    private val mapper: WorldBankMapper
) : WorldBankApi {

    override fun getAllHistoryGDPbyCountry(countryCode: String): List<GrossDomesticProduct> {
        return client.getAllHistoryGDPbyCountry(countryCode)
            ?.let { mapper.toDomainCurrentGDP(it, countryCode) } ?: emptyList()
    }

    override fun getAllHistoryPercentageToGDPByCountry(countryCode: String): List<Debt> {
        return client.getAllHistoryPercentageDebtToGDPbyCountry(countryCode)
            ?.let { mapper.toDomainPercentageToGDP(it, countryCode) } ?: emptyList()
    }

    override fun getAllHistoryReservesAmountByCountry(countryCode: String): List<InternationalReserve> {
        return client.getAllHistoryReservesAmountByCountry(countryCode)
            ?.let { mapper.toDomainReservesAmount(it, countryCode) } ?: emptyList()
    }

    override fun getAllHistoryPopulationByCountry(countryCode: String): List<Population> {
        return client.getAllHistoryPopulationByCountry(countryCode)
            ?.let { mapper.toDomainPopulation(it, countryCode) } ?: emptyList()
    }
}
