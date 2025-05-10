package org.wcm.usecase.api

import org.wcm.domain.model.Debt

interface DebtApi {
    fun getByCountryCode(countryCode: String): List<Debt>
}