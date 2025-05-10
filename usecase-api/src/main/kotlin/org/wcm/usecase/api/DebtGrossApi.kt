package org.wcm.usecase.api

import org.wcm.domain.model.DebtGross

interface DebtGrossApi {

    fun getByCountryCode(countryCode: String): List<DebtGross>

    fun getByCountryFromDataBase(countryCode: String): List<DebtGross>
}