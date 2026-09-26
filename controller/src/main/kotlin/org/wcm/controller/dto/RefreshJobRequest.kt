package org.wcm.controller.dto

import jakarta.validation.constraints.NotBlank

data class RefreshJobRequest(
    @field:NotBlank
    val feature: String,
    val countryCode: String? = null
)