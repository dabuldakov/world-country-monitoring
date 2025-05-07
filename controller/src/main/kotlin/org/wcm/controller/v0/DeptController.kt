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
import org.wcm.domain.model.Dept
import org.wcm.domain.model.DeptGross
import org.wcm.usecase.api.DeptApi
import org.wcm.usecase.api.DeptGrossApi

@Tag(name = "DeptController_v0", description = "Контроллер долга v0")
@RequestMapping(PathConstant.DEPT)
@RestController("DeptController_v0")
class DeptController(
    private val api: DeptApi,
    private val deptGrossApi: DeptGrossApi,
) {

    @Operation(
        summary = "Получить долг по стране",
        operationId = "getByCountry"
    )
    @GetMapping(value = ["country/{country}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getByCountry(@PathVariable country: String): ResponseEntity<List<Dept>> {
        return ResponseEntity.ok(api.getByCountryCode(country))
    }

    @Operation(
        summary = "Получить отношение внешнего долга к номанальному ВВП по стране",
        operationId = "getByCountry"
    )
    @GetMapping(value = ["dept-gross/country/{country}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getDeptGrossByCountry(@PathVariable country: String): ResponseEntity<List<DeptGross>> {
        return ResponseEntity.ok(deptGrossApi.getByCountryCode(country))
    }
}