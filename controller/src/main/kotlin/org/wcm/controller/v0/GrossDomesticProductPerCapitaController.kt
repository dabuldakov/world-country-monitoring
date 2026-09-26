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
import org.wcm.domain.model.GrossDomesticProductPerCapita
import org.wcm.usecase.api.GrossDomesticProductPerCapitaApi

@Tag(name = "GrossDomesticProductPerCapitaController_v0", description = "GDP per capita controller v0")
@RequestMapping(PathConstant.GROSS_DOMESTIC_PRODUCT_PER_CAPITA)
@RestController("GrossDomesticProductPerCapitaController_v0")
class GrossDomesticProductPerCapitaController(
    private val api: GrossDomesticProductPerCapitaApi
) {

    @Operation(
        summary = "Get GDP per capita by country",
        operationId = "getGdpPerCapitaByCountry"
    )
    @GetMapping(value = ["country/{country}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getByCountry(@PathVariable country: String): ResponseEntity<List<GrossDomesticProductPerCapita>> {
        return ResponseEntity.ok(api.getByCountryCode(country))
    }

    @Operation(
        summary = "Get GDP per capita for all countries by year",
        operationId = "getAllGdpPerCapitaByDate"
    )
    @GetMapping(value = ["year/{year}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllCountriesByDate(@PathVariable year: String): ResponseEntity<List<GrossDomesticProductPerCapita>> {
        return ResponseEntity.ok(api.getAllCountriesByYear(Utils.convertYearToLocalDate(year)))
    }
}