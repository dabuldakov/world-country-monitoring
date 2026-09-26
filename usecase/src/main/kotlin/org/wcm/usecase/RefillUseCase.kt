package org.wcm.usecase

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.wcm.domain.api.*
import org.wcm.domain.model.RefillFeature
import org.wcm.usecase.api.RefillApi

@Component
class RefillUseCase(
    private val worldBankApi: WorldBankApi,
    private val countryAdapter: CountryAdapter,
    private val gDPAdapter: GrossDomesticProductAdapter,
    private val debtAdapter: DebtAdapter,
    private val internationalReserveAdapter: InternationalReserveAdapter,
    private val populationAdapter: PopulationAdapter,
) : RefillApi {

    private val logger: Logger = LoggerFactory.getLogger(RefillUseCase::class.java)

    override fun forAllCountries() {
        countryAdapter.getAll().forEach {
            forCountry(it.code)
            logger.info("Updated for ${it.code}")
        }
    }

    override fun forCountry(countryCode: String) {
        RefillFeature.entries.forEach { feature -> forCountry(feature, countryCode) }
    }

    override fun forAllCountries(feature: RefillFeature) {
        countryAdapter.getAll().forEach {
            forCountry(feature, it.code)
            logger.info("Updated ${feature.key} for ${it.code}")
        }
    }

    override fun forCountry(feature: RefillFeature, countryCode: String) {
        when (feature) {
            RefillFeature.GDP -> worldBankApi.getAllHistoryGDPbyCountry(countryCode)
                .let { gDPAdapter.saveAll(it) }

            RefillFeature.DEBT -> worldBankApi.getAllHistoryPercentageToGDPByCountry(countryCode)
                .let { debtAdapter.saveAll(it) }

            RefillFeature.RESERVES -> worldBankApi.getAllHistoryReservesAmountByCountry(countryCode)
                .let { internationalReserveAdapter.saveAll(it) }

            RefillFeature.POPULATION -> worldBankApi.getAllHistoryPopulationByCountry(countryCode)
                .let { populationAdapter.saveAll(it) }
        }
    }
}