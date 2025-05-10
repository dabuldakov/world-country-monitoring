package org.wcm.usecase.api

import org.wcm.domain.model.Debt
import java.time.LocalDate

interface DebtApi {
    fun getByCountryCode(countryCode: String): List<Debt>
    fun getAllCountriesByYear(date: LocalDate): List<Debt>
}