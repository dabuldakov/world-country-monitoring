package org.wcm.usecase

import org.springframework.stereotype.Component
import org.wcm.domain.api.GrossDomesticProductAdapter
import org.wcm.domain.model.GrossDomesticProduct
import org.wcm.usecase.api.GrossDomesticProductApi
import java.time.LocalDate

@Component
class GrossDomesticProductUseCase(
    private val adapter: GrossDomesticProductAdapter
) : GrossDomesticProductApi {

    override fun findAllByCountryCode(countryCode: String): List<GrossDomesticProduct> {
        return adapter.getByCountryCode(countryCode)
    }

    override fun findAllCountriesByDate(date: LocalDate): List<GrossDomesticProduct> {
        return adapter.findAllCountriesByDate(date)
    }
}