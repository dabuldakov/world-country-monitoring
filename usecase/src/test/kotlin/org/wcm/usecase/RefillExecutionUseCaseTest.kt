package org.wcm.usecase

import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.wcm.domain.api.CountryAdapter
import org.wcm.domain.api.RefillStatusAdapter
import org.wcm.domain.model.Country
import org.wcm.domain.model.RefillFeature
import org.wcm.domain.model.RefillFeatureStatus
import org.wcm.usecase.api.RefillApi
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class RefillExecutionUseCaseTest {

    private val refillApi = mock<RefillApi>()
    private val countryAdapter = mock<CountryAdapter>()
    private val refillStatusAdapter = mock<RefillStatusAdapter>()
    private val useCase = RefillExecutionUseCase(refillApi, countryAdapter, refillStatusAdapter)

    @Test
    fun `records successful full refill and statuses for all features`() {
        whenever(countryAdapter.getAll()).thenReturn(
            listOf(Country("RUS", "Russia"), Country("USA", "United States"))
        )

        val result = useCase.updateAllCountries()

        assertEquals("SUCCESS", result.status)
        assertEquals(2, result.processedCount)
        assertEquals("all", result.operation)
        verify(refillApi).forAllCountries()
        assertSame(result, useCase.lastResult())

        val statuses = argumentCaptor<List<RefillFeatureStatus>>()
        verify(refillStatusAdapter).saveAll(statuses.capture())
        assertEquals(RefillFeature.entries.size, statuses.firstValue.size)
    }

    @Test
    fun `updates only the requested feature`() {
        whenever(countryAdapter.getAll()).thenReturn(listOf(Country("RUS", "Russia")))

        val result = useCase.updateFeatureCountry(RefillFeature.POPULATION, "RUS")

        assertEquals("SUCCESS", result.status)
        assertEquals("population:RUS", result.operation)
        verify(refillApi).forCountry(RefillFeature.POPULATION, "RUS")

        val statuses = argumentCaptor<List<RefillFeatureStatus>>()
        verify(refillStatusAdapter).saveAll(statuses.capture())
        assertEquals("population", statuses.firstValue.single().feature)
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