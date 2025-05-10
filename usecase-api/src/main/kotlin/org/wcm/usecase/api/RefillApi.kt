package org.wcm.usecase.api

interface RefillApi {

    fun forAllCountries()

    fun forCountry(countryCode: String)
}