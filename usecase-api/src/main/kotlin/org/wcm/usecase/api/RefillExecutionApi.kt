package org.wcm.usecase.api

import org.wcm.domain.model.RefillExecutionResult
import org.wcm.domain.model.RefillFeature
import org.wcm.domain.model.RefillFeatureStatus

interface RefillExecutionApi {

    fun updateAllCountries(): RefillExecutionResult

    fun updateCountry(countryCode: String): RefillExecutionResult

    fun updateFeatureAllCountries(feature: RefillFeature): RefillExecutionResult

    fun updateFeatureCountry(feature: RefillFeature, countryCode: String): RefillExecutionResult

    fun lastResult(): RefillExecutionResult?

    fun featureStatuses(): List<RefillFeatureStatus>
}
