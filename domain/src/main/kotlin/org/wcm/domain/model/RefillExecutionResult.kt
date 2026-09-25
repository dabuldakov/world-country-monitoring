package org.wcm.domain.model

import java.time.Instant

data class RefillExecutionResult(
    val operation: String,
    val startedAt: Instant,
    val finishedAt: Instant,
    val status: String,
    val processedCount: Int,
    val errorMessage: String? = null
)
