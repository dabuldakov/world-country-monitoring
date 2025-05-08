package org.wcm.domain.api

import org.wcm.domain.model.Country

interface CountryAdapter {

    fun getAll(): List<Country>
}