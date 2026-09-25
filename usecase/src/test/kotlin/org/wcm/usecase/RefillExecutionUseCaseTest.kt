package org.wcm.usecase

import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.wcm.domain.api.CountryAdapter
import org.wcm.domain.model.Country
import org.wcm.usecase.api.RefillApi
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class RefillExecutionUseCaseTest {

    private val refillApi = mock<RefillApi>()
    private val countryAdapter = mock<CountryAdapter>()
    private val useCase = RefillExecutionUseCase(refillApi, countryAdapter)

    @Test
    fun `records successful full refill`() {
        whenever(countryAdapter.getAll()).thenReturn(
            listOf(Country("RUS", "Russia"), Country("USA", "United States"))
        )

        val result = useCase.updateAllCountries()

        assertEquals("SUCCESS", result.status)
        assertEquals(2, result.processedCount)
        assertEquals("all", result.operation)
        verify(refillApi).forAllCountries()
        assertSame(result, useCase.lastResult())
    }

    @Test
    fun `records failed refill with error message`() {
        doThrow(RuntimeException("world bank unavailable")).whenever(refillApi).forCountry("RUS")

        val result = useCase.updateCountry("RUS")

        assertEquals("FAILED", result.status)
        assertEquals("RUS", result.operation)
        assertEquals(1, result.processedCount)
        assertEquals("world bank unavailable", result.errorMessage)
        assertSame(result, useCase.lastResult())
    }
}
