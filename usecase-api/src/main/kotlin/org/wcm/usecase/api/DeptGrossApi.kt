package org.wcm.usecase.api

import org.wcm.domain.model.DeptGross

interface DeptGrossApi {

    fun getByCountryCode(countryCode: String): List<DeptGross>
}