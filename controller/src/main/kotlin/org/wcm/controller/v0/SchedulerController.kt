package org.wcm.controller.v0

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.wcm.controller.PathConstant
import org.wcm.domain.model.RefillExecutionResult
import org.wcm.usecase.api.RefillExecutionApi

@Tag(name = "SchedulerController_v0", description = "Country controller v0")
@RequestMapping(PathConstant.SCHEDULER)
@RestController("SchedulerController_v0")
class SchedulerController(
    private val refillExecutionApi: RefillExecutionApi
) {

    @Operation(
        summary = "updateAllCountries",
        operationId = "updateAllCountries"
    )
    @GetMapping(value = ["/update/all"])
    fun updateAllCountries(): ResponseEntity<RefillExecutionResult> =
        ResponseEntity.ok(refillExecutionApi.updateAllCountries())

    @Operation(
        summary = "Update GDP, debt, reserves and population",
        operationId = "updateCountry"
    )
    @GetMapping(value = ["/update/country/{code}"])
    fun updateCountry(@PathVariable code: String): ResponseEntity<RefillExecutionResult> =
        ResponseEntity.ok(refillExecutionApi.updateCountry(code))
}