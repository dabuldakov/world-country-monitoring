package org.wcm.controller.v0

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.wcm.controller.PathConstant
import org.wcm.controller.dto.VisitResponse
import org.wcm.usecase.api.VisitApi

@Tag(name = "VisitController_v0", description = "Visit controller v0")
@RequestMapping(PathConstant.VISIT)
@RestController("VisitController_v0")
class VisitController(
    private val visitApi: VisitApi
) {

    @Operation(summary = "Register site visit", operationId = "registerVisit")
    @PostMapping
    fun register(): ResponseEntity<VisitResponse> =
        ResponseEntity.ok(VisitResponse(visitApi.registerVisit().count))
}
