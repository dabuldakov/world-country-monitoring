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
import org.wcm.domain.model.InternationalReserve
import org.wcm.usecase.api.InternationalReserveApi

@Tag(name = "InternationalReserveController_v0", description = "Контроллер международных резервов v0")
@RequestMapping(PathConstant.INTERNATIONAL_RESERVE)
@RestController("InternationalReserveController_v0")
class InternationalReserveController(
    private val internationalReserveApi: InternationalReserveApi
) {

    @Operation(
        summary = "Получить международный резерв",
        operationId = "get"
    )
    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get(@PathVariable id: Long): ResponseEntity<InternationalReserve>{
        return ResponseEntity.ok(internationalReserveApi.getBiId(id))
    }

    @Operation(
        summary = "Получить международный резерв по стране",
        operationId = "getByCountry"
    )
    @GetMapping(value = ["country/{country}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getByCountry(@PathVariable country: String): ResponseEntity<List<InternationalReserve>>{
        return ResponseEntity.ok(internationalReserveApi.getByCountryCode(country))
    }
}