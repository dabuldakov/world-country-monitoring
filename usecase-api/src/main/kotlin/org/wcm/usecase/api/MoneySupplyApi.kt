package org.wcm.usecase.api

import org.wcm.domain.model.MoneySupply
import java.time.LocalDate

interface MoneySupplyApi {
    fun findAllByCountryCode(countryCode: String): List<MoneySupply>
    fun findAllCountriesByDate(date: LocalDate): List<MoneySupply>
}