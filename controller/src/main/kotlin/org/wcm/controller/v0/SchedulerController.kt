package org.wcm.controller.v0

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.wcm.controller.PathConstant
import org.wcm.usecase.api.RefillApi

@Tag(name = "SchedulerController_v0", description = "Country controller v0")
@RequestMapping(PathConstant.SCHEDULER)
@RestController("SchedulerController_v0")
class SchedulerController(
    private val refillApi: RefillApi
) {

    @Operation(
        summary = "updateAllCountries",
        operationId = "updateAllCountries"
    )
    @GetMapping(value = ["/update/all"])
    fun updateAllCountries(): ResponseEntity<String> {
        refillApi.forAllCountries()
        return ResponseEntity.ok("OK")
    }

    @Operation(
        summary = "update GDP, percentage Debt to GDP",
        operationId = "updateCountry"
    )
    @GetMapping(value = ["/update/country/{code}"])
    fun updateCountry(@PathVariable code: String): ResponseEntity<String> {
        refillApi.forCountry(code)
        return ResponseEntity.ok("OK")
    }
}