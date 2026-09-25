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
import org.wcm.domain.model.Population
import org.wcm.usecase.api.PopulationApi

@Tag(name = "PopulationController_v0", description = "Population controller v0")
@RequestMapping(PathConstant.POPULATION)
@RestController("PopulationController_v0")
class PopulationController(
    private val api: PopulationApi
) {

    @Operation(
        summary = "Get population by country",
        operationId = "getPopulationByCountry"
    )
    @GetMapping(value = ["country/{country}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getByCountry(@PathVariable country: String): ResponseEntity<List<Population>> {
        return ResponseEntity.ok(api.findAllByCountryCode(country))
    }

    @Operation(
        summary = "Get population for all countries by year",
        operationId = "getAllPopulationByDate"
    )
    @GetMapping(value = ["year/{year}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllCountriesByDate(@PathVariable year: String): ResponseEntity<List<Population>> {
        return ResponseEntity.ok(api.findAllCountriesByDate(Utils.convertYearToLocalDate(year)))
    }
}
