package org.wcm.domain.api

import org.wcm.domain.model.RefillCountryStatus
import org.wcm.domain.model.RefreshJob
import org.wcm.domain.model.RefreshJobItem

interface RefreshJobAdapter {

    fun saveJob(job: RefreshJob): RefreshJob

    fun findJobById(id: Long): RefreshJob?

    fun findActiveJob(feature: String, countryCode: String?): RefreshJob?

    fun findJobs(feature: String?, status: String?): List<RefreshJob>

    fun claimNextQueuedJob(): RefreshJob?

    fun saveItems(items: List<RefreshJobItem>)

    fun findItems(jobId: Long): List<RefreshJobItem>

    fun updateItem(item: RefreshJobItem): RefreshJobItem

    fun saveCountryStatuses(statuses: List<RefillCountryStatus>)

    fun findCountryStatuses(feature: String?): List<RefillCountryStatus>
}