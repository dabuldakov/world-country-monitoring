package org.wcm.usecase

import org.springframework.stereotype.Component
import org.wcm.domain.api.MoneySupplyAdapter
import org.wcm.domain.model.MoneySupply
import org.wcm.usecase.api.MoneySupplyApi
import java.time.LocalDate

@Component
class MoneySupplyUseCase(
    private val adapter: MoneySupplyAdapter
) : MoneySupplyApi {
    override fun findAllByCountryCode(countryCode: String): List<MoneySupply> {
        return adapter.findAllByCountryCode(countryCode)
    }

    override fun findAllCountriesByDate(date: LocalDate): List<MoneySupply> {
        return adapter.findAllCountriesByDate(date)
    }
}