package org.wcm.usecase

import org.springframework.stereotype.Component
import org.wcm.domain.api.GrossDomesticProductPerCapitaAdapter
import org.wcm.domain.model.GrossDomesticProductPerCapita
import org.wcm.usecase.api.GrossDomesticProductPerCapitaApi
import java.time.LocalDate

@Component
class GrossDomesticProductPerCapitaUseCase(
    private val adapter: GrossDomesticProductPerCapitaAdapter
) : GrossDomesticProductPerCapitaApi {

    override fun getByCountryCode(countryCode: String): List<GrossDomesticProductPerCapita> =
        adapter.getByCountryCode(countryCode)

    override fun getAllCountriesByYear(date: LocalDate): List<GrossDomesticProductPerCapita> =
        adapter.getAllCountriesByYear(date)
}