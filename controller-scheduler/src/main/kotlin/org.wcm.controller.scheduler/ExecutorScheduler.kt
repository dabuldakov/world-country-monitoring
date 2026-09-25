package org.wcm.controller.scheduler

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.wcm.usecase.api.RefillExecutionApi

@Component
class ExecutorScheduler(
    private val refillExecutionApi: RefillExecutionApi
) {
    private val logger: Logger = LoggerFactory.getLogger(ExecutorScheduler::class.java)

    @Scheduled(cron = "\${application.scheduling.update-all-countries-gdp}")
    fun updateWorldBankDataForAllCountries() {
        logger.info("Start update data from World Bank for all countries")
        val result = refillExecutionApi.updateAllCountries()
        logger.info("Stop update data from World Bank for all countries: status=${result.status}, processed=${result.processedCount}")
    }
}