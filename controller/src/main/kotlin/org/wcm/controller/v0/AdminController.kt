package org.wcm.controller.v0

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.wcm.controller.PathConstant
import org.wcm.controller.dto.LoginRequest
import org.wcm.controller.dto.LoginResponse
import org.wcm.controller.dto.VisitResponse
import org.wcm.domain.model.Feedback
import org.wcm.domain.model.RefillExecutionResult
import org.wcm.usecase.api.AdminAuthApi
import org.wcm.usecase.api.FeedbackApi
import org.wcm.usecase.api.RefillExecutionApi
import org.wcm.usecase.api.VisitApi

@Tag(name = "AdminController_v0", description = "Admin cabinet controller v0")
@RequestMapping(PathConstant.ADMIN)
@RestController("AdminController_v0")
class AdminController(
    private val adminAuthApi: AdminAuthApi,
    private val feedbackApi: FeedbackApi,
    private val visitApi: VisitApi,
    private val refillExecutionApi: RefillExecutionApi
) {

    @Operation(summary = "Login to admin cabinet", operationId = "adminLogin")
    @PostMapping(value = ["/login"])
    fun login(@Valid @RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        val token = adminAuthApi.login(request.login, request.password)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        return ResponseEntity.ok(LoginResponse(token))
    }

    @Operation(summary = "Get feedback list", operationId = "adminGetFeedback")
    @GetMapping(value = ["/feedback"])
    fun getFeedback(): ResponseEntity<List<Feedback>> = ResponseEntity.ok(feedbackApi.findAll())

    @Operation(summary = "Get visits count", operationId = "adminGetVisits")
    @GetMapping(value = ["/visits"])
    fun getVisits(): ResponseEntity<VisitResponse> =
        ResponseEntity.ok(VisitResponse(visitApi.getStatistics().count))

    @Operation(summary = "Get last refill result", operationId = "adminGetLastRefill")
    @GetMapping(value = ["/refill/last"])
    fun getLastRefill(): ResponseEntity<RefillExecutionResult> {
        val result = refillExecutionApi.lastResult()
        return if (result == null) ResponseEntity.noContent().build() else ResponseEntity.ok(result)
    }

    @Operation(summary = "Update all countries", operationId = "adminRefillAll")
    @PostMapping(value = ["/refill/all"])
    fun refillAll(): ResponseEntity<RefillExecutionResult> =
        ResponseEntity.ok(refillExecutionApi.updateAllCountries())

    @Operation(summary = "Update one country", operationId = "adminRefillCountry")
    @PostMapping(value = ["/refill/country/{code}"])
    fun refillCountry(@PathVariable code: String): ResponseEntity<RefillExecutionResult> =
        ResponseEntity.ok(refillExecutionApi.updateCountry(code))
}
