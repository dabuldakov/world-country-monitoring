package org.wcm.controller.scheduler

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.wcm.usecase.api.RefillApi

@Component
class ExecutorScheduler(
    private val refillApi: RefillApi
) {
    private val logger: Logger = LoggerFactory.getLogger(ExecutorScheduler::class.java)

    @Scheduled(cron = "\${application.scheduling.update-all-countries-gdp}")
    fun updateGDPForAllCountries() {
        logger.info("Start update data from World Bank for all countries")
        refillApi.forAllCountries()
        logger.info("Stop update data from World Bank for all countries")
    }
}