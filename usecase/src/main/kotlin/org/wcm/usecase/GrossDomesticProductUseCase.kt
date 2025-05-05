package org.wcm.usecase

import org.springframework.stereotype.Component
import org.wcm.domain.api.GrossDomesticProductAdapter
import org.wcm.domain.model.GrossDomesticProduct
import org.wcm.usecase.api.GrossDomesticProductApi

@Component
class GrossDomesticProductUseCase(
    private val adapter: GrossDomesticProductAdapter
) : GrossDomesticProductApi {

    override fun getByCountryCode(countryCode: String): List<GrossDomesticProduct> {
        return adapter.getByCountryCode(countryCode)
    }
}