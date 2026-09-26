package org.wcm.controller.scheduler

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.wcm.usecase.api.RefreshJobApi

@Component
@ConditionalOnProperty(
    prefix = "application.refill.worker",
    name = ["enabled"],
    havingValue = "true",
    matchIfMissing = true
)
class RefreshJobWorker(
    private val refreshJobApi: RefreshJobApi
) {
    private val logger: Logger = LoggerFactory.getLogger(RefreshJobWorker::class.java)

    @Scheduled(fixedDelayString = "\${application.refill.worker.delay-ms:5000}")
    fun processNextJob() {
        val processed = refreshJobApi.processNextJob()
        if (processed) {
            logger.info("Refresh job processed")
        }
    }
}