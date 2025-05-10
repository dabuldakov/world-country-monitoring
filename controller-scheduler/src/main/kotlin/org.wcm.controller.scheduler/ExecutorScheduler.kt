package org.wcm.controller.scheduler

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.wcm.usecase.api.RefillApi

@Component
class ExecutorScheduler(
    private val refillApi: RefillApi
) {

    @Scheduled(cron = "\${application.scheduling.update-all-countries-gdp}")
    fun updateGDPForAllCountries() {
        refillApi.forAllCountries()
    }
}