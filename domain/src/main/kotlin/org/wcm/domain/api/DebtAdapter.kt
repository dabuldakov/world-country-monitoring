package org.wcm.domain.api

import org.wcm.domain.model.Debt
import java.time.LocalDate

interface DebtAdapter {
    fun getByCountryCode(countryCode: String): List<Debt>
    fun getAllCountriesByYear(data: LocalDate): List<Debt>
    fun saveAll(debts: List<Debt>)
}