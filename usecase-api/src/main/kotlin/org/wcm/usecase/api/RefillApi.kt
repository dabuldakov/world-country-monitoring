package org.wcm.usecase.api

import org.wcm.domain.model.RefillFeature

interface RefillApi {

    fun forAllCountries()

    fun forCountry(countryCode: String)

    fun forAllCountries(feature: RefillFeature)

    fun forCountry(feature: RefillFeature, countryCode: String)
}