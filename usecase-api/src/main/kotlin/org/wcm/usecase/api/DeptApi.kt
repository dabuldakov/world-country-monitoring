package org.wcm.usecase.api

import org.wcm.domain.model.Dept

interface DeptApi {
    fun getByCountryCode(countryCode: String): List<Dept>
}