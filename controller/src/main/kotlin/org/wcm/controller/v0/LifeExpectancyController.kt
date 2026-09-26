package org.wcm.controller.v0

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.wcm.controller.PathConstant
import org.wcm.domain.Utils
import org.wcm.domain.model.LifeExpectancy
import org.wcm.usecase.api.LifeExpectancyApi

@Tag(name = "LifeExpectancyController_v0", description = "Life expectancy controller v0")
@RequestMapping(PathConstant.LIFE_EXPECTANCY)
@RestController("LifeExpectancyController_v0")
class LifeExpectancyController(
    private val api: LifeExpectancyApi
) {

    @Operation(
        summary = "Get life expectancy by country",
        operationId = "getLifeExpectancyByCountry"
    )
    @GetMapping(value = ["country/{country}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getByCountry(@PathVariable country: String): ResponseEntity<List<LifeExpectancy>> {
        return ResponseEntity.ok(api.getByCountryCode(country))
    }

    @Operation(
        summary = "Get life expectancy for all countries by year",
        operationId = "getAllLifeExpectancyByDate"
    )
    @GetMapping(value = ["year/{year}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllCountriesByDate(@PathVariable year: String): ResponseEntity<List<LifeExpectancy>> {
        return ResponseEntity.ok(api.getAllCountriesByYear(Utils.convertYearToLocalDate(year)))
    }
}