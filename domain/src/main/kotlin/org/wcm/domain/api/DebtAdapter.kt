package org.wcm.domain.api

import org.wcm.domain.model.Debt

interface DebtAdapter {
    fun getByCountryCode(countryCode: String): List<Debt>
    fun saveAll(debts: List<Debt>)
}