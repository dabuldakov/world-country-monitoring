package org.wcm.controller.v0

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.wcm.controller.PathConstant
import org.wcm.controller.dto.FeedbackRequest
import org.wcm.domain.model.Feedback
import org.wcm.usecase.api.FeedbackApi

@Tag(name = "FeedbackController_v0", description = "Feedback controller v0")
@RequestMapping(PathConstant.FEEDBACK)
@RestController("FeedbackController_v0")
class FeedbackController(
    private val feedbackApi: FeedbackApi
) {

    @Operation(summary = "Submit feedback", operationId = "submitFeedback")
    @PostMapping
    fun create(@Valid @RequestBody request: FeedbackRequest): ResponseEntity<Feedback> =
        ResponseEntity.status(HttpStatus.CREATED).body(feedbackApi.create(request.email, request.message))
}
