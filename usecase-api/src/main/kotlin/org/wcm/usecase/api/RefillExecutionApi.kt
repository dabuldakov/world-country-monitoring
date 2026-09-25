package org.wcm.usecase.api

import org.wcm.domain.model.RefillExecutionResult

interface RefillExecutionApi {

    fun updateAllCountries(): RefillExecutionResult

    fun updateCountry(countryCode: String): RefillExecutionResult

    fun lastResult(): RefillExecutionResult?
}
