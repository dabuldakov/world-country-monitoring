package org.wcm.usecase

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.wcm.domain.api.CountryAdapter
import org.wcm.domain.api.DebtAdapter
import org.wcm.domain.api.GrossDomesticProductAdapter
import org.wcm.domain.api.WorldBankApi
import org.wcm.usecase.api.RefillApi

@Component
class RefillUseCase(
    private val worldBankApi: WorldBankApi,
    private val countryAdapter: CountryAdapter,
    private val gDPAdapter: GrossDomesticProductAdapter,
    private val debtAdapter: DebtAdapter,
) : RefillApi {

    private val logger: Logger = LoggerFactory.getLogger(RefillUseCase::class.java)

    override fun forAllCountries() {
        countryAdapter.getAll().forEach {
            forCountry(it.code)
            logger.info("Updated for ${it.code}")
        }
    }

    override fun forCountry(countryCode: String) {
        worldBankApi.getAllHistoryGDPbyCountry(countryCode).let { gDPAdapter.saveAll(it) }
        worldBankApi.getAllHistoryPercentageToGDPByCountry(countryCode).let { debtAdapter.saveAll(it) }
    }
}