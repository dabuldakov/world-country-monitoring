package org.wcm.usecase.api

import org.wcm.domain.model.Country

interface CountryApi {

    fun getAll(): List<Country>
}