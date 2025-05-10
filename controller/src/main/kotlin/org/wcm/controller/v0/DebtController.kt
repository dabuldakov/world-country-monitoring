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
import org.wcm.domain.model.Debt
import org.wcm.domain.model.DebtGross
import org.wcm.usecase.api.DebtApi
import org.wcm.usecase.api.DebtGrossApi

@Tag(name = "DebtController_v0", description = "Контроллер долга v0")
@RequestMapping(PathConstant.DEBT)
@RestController("DebtController_v0")
class DebtController(
    private val api: DebtApi,
    private val debtGrossApi: DebtGrossApi,
) {

    @Operation(
        summary = "Получить долг по стране",
        operationId = "getByCountry"
    )
    @GetMapping(value = ["country/{country}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getByCountry(@PathVariable country: String): ResponseEntity<List<Debt>> {
        return ResponseEntity.ok(api.getByCountryCode(country))
    }

    @Operation(
        summary = "Get debts for all countries by year",
        operationId = "getAllCountriesByYear"
    )
    @GetMapping(value = ["year/{year}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllCountriesByYear(@PathVariable year: String): ResponseEntity<List<Debt>> {
        return ResponseEntity.ok(api.getAllCountriesByYear(Utils.convertYearToLocalDate(year)))
    }

    @Operation(
        summary = "Получить отношение внешнего долга к номанальному ВВП по стране",
        operationId = "getByCountry"
    )
    @GetMapping(value = ["debt-gross/country/{country}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getDebtGrossByCountry(@PathVariable country: String): ResponseEntity<List<DebtGross>> {
        return ResponseEntity.ok(debtGrossApi.getByCountryFromDataBase(country))
    }
}