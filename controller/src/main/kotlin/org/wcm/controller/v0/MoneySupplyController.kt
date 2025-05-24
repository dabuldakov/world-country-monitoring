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
import org.wcm.domain.model.MoneySupply
import org.wcm.usecase.api.MoneySupplyApi

@Tag(name = "MoneySupplyController_v0", description = "Money supply controller v0")
@RequestMapping(PathConstant.MONEY_SUPPLY)
@RestController("MoneySupplyController_v0")
class MoneySupplyController(
    private val usecase: MoneySupplyApi
) {
    @Operation(
        summary = "Get money supply by country",
        operationId = "getByCountry"
    )
    @GetMapping(value = ["country/{country}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getByCountry(@PathVariable country: String): ResponseEntity<List<MoneySupply>> {
        return ResponseEntity.ok(usecase.findAllByCountryCode(country))
    }

    @Operation(
        summary = "Get money supply all countries by date",
        operationId = "getAllCountiesByDate"
    )
    @GetMapping(value = ["year/{year}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllCountiesByDate(@PathVariable year: String): ResponseEntity<List<MoneySupply>> {
        return ResponseEntity.ok(usecase.findAllCountriesByDate(Utils.convertYearToLocalDate(year)))
    }
}