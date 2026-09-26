package org.wcm.usecase

import org.springframework.stereotype.Component
import org.wcm.domain.api.LifeExpectancyAdapter
import org.wcm.domain.model.LifeExpectancy
import org.wcm.usecase.api.LifeExpectancyApi
import java.time.LocalDate

@Component
class LifeExpectancyUseCase(
    private val adapter: LifeExpectancyAdapter
) : LifeExpectancyApi {

    override fun getByCountryCode(countryCode: String): List<LifeExpectancy> =
        adapter.getByCountryCode(countryCode)

    override fun getAllCountriesByYear(date: LocalDate): List<LifeExpectancy> =
        adapter.getAllCountriesByYear(date)
}