package org.wcm.controller.scheduler

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.wcm.domain.model.RefillFeature
import org.wcm.usecase.api.RefreshJobApi

@Component
class ExecutorScheduler(
    private val refreshJobApi: RefreshJobApi
) {
    private val logger: Logger = LoggerFactory.getLogger(ExecutorScheduler::class.java)

    @Scheduled(cron = "\${application.scheduling.update-all-countries-gdp}")
    fun updateWorldBankDataForAllCountries() {
        logger.info("Queue refresh jobs for all features")
        RefillFeature.entries.forEach { feature ->
            val job = refreshJobApi.enqueue(feature, null)
            logger.info("Queued {} refresh as job {}", feature.key, job.id)
        }
    }
}