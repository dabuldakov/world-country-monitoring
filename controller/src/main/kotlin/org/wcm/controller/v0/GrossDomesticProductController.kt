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
import org.wcm.domain.model.GrossDomesticProduct
import org.wcm.usecase.api.GrossDomesticProductApi

@Tag(name = "GrossDomesticProductController_v0", description = "Контроллер ВВП v0")
@RequestMapping(PathConstant.GROSS_DOMESTIC_PRODUCT)
@RestController("GrossDomesticProductController_v0")
class GrossDomesticProductController(
    private val usecase: GrossDomesticProductApi
) {
    @Operation(
        summary = "Получить ВВП по стране",
        operationId = "getByCountry"
    )
    @GetMapping(value = ["country/{country}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getByCountry(@PathVariable country: String): ResponseEntity<List<GrossDomesticProduct>> {
        return ResponseEntity.ok(usecase.findAllByCountryCode(country))
    }

    @Operation(
        summary = "Получить ВВП всех стран по году",
        operationId = "getAllCountiesByDate"
    )
    @GetMapping(value = ["year/{year}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllCountiesByDate(@PathVariable year: String): ResponseEntity<List<GrossDomesticProduct>> {
        return ResponseEntity.ok(usecase.findAllCountriesByDate(Utils.convertYearToLocalDate(year)))
    }
}