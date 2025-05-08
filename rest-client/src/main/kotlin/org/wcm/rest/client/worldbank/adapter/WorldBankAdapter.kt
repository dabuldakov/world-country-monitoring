package org.wcm.rest.client.worldbank.adapter

import org.springframework.stereotype.Component
import org.wcm.domain.api.WorldBankApi
import org.wcm.domain.model.GrossDomesticProduct
import org.wcm.rest.client.worldbank.WorldBankClient
import org.wcm.rest.client.worldbank.mapper.WorldBankMapper

@Component
class WorldBankAdapter(
    private val client: WorldBankClient,
    private val mapper: WorldBankMapper
): WorldBankApi {

    override fun getAllHistoryGDPbyCountry(countryCode: String): List<GrossDomesticProduct> {
        return client.getAllHistoryGDPbyCountry(countryCode)
            ?.let { mapper.toDomain(it, countryCode) } ?: emptyList()
    }
}