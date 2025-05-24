package org.wcm.domain.api

import org.wcm.domain.model.MoneySupply
import java.time.LocalDate

interface MoneySupplyAdapter {
    fun findAllByCountryCode(countryCode: String): List<MoneySupply>
    fun findAllCountriesByDate(date: LocalDate): List<MoneySupply>
}