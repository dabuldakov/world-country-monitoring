package org.wcm.usecase

import org.springframework.stereotype.Component
import org.wcm.domain.api.CountryAdapter
import org.wcm.domain.model.RefillExecutionResult
import org.wcm.usecase.api.RefillApi
import org.wcm.usecase.api.RefillExecutionApi
import java.time.Instant
import java.util.concurrent.atomic.AtomicReference

@Component
class RefillExecutionUseCase(
    private val refillApi: RefillApi,
    private val countryAdapter: CountryAdapter
) : RefillExecutionApi {

    private val lastResult = AtomicReference<RefillExecutionResult?>(null)

    @Synchronized
    override fun updateAllCountries(): RefillExecutionResult {
        val processedCount = runCatching { countryAdapter.getAll().size }.getOrDefault(0)
        return execute("all", processedCount) { refillApi.forAllCountries() }
    }

    @Synchronized
    override fun updateCountry(countryCode: String): RefillExecutionResult =
        execute(countryCode, 1) { refillApi.forCountry(countryCode) }

    override fun lastResult(): RefillExecutionResult? = lastResult.get()

    private fun execute(
        operation: String,
        processedCount: Int,
        action: () -> Unit
    ): RefillExecutionResult {
        val startedAt = Instant.now()
        val result = try {
            action()
            RefillExecutionResult(
                operation = operation,
                startedAt = startedAt,
                finishedAt = Instant.now(),
                status = "SUCCESS",
                processedCount = processedCount
            )
        } catch (exception: Exception) {
            RefillExecutionResult(
                operation = operation,
                startedAt = startedAt,
                finishedAt = Instant.now(),
                status = "FAILED",
                processedCount = processedCount,
                errorMessage = exception.message
            )
        }

        lastResult.set(result)
        return result
    }
}
