package org.wcm.controller.v0

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.wcm.controller.PathConstant
import org.wcm.domain.model.Country
import org.wcm.usecase.api.CountryApi

@Tag(name = "CountryController_v0", description = "Country controller v0")
@RequestMapping(PathConstant.COUNTRY)
@RestController("CountryController_v0")
class CountryController(
    private val api: CountryApi
) {
    @Operation(
        summary = "Get all countries",
        operationId = "getAll"
    )
    @GetMapping(value = ["/all"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAll(): ResponseEntity<List<Country>> =
        ResponseEntity.ok(api.getAll())
}