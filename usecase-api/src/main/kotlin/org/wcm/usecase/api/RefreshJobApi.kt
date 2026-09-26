package org.wcm.usecase.api

import org.wcm.domain.model.RefillCountryStatus
import org.wcm.domain.model.RefillFeature
import org.wcm.domain.model.RefreshJob

interface RefreshJobApi {

    fun enqueue(feature: RefillFeature, countryCode: String?): RefreshJob

    fun retryFailed(jobId: Long): RefreshJob?

    fun get(jobId: Long): RefreshJob?

    fun list(feature: RefillFeature?, status: String?): List<RefreshJob>

    fun countryStatuses(feature: RefillFeature?): List<RefillCountryStatus>

    fun processNextJob(): Boolean
}