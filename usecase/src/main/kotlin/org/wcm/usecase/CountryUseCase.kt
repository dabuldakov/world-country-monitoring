package org.wcm.usecase

import org.springframework.stereotype.Component
import org.wcm.domain.api.CountryAdapter
import org.wcm.usecase.api.CountryApi

@Component
class CountryUseCase(
    private val countryAdapter: CountryAdapter
) : CountryApi {

    override fun getAll() = countryAdapter.getAll()
}