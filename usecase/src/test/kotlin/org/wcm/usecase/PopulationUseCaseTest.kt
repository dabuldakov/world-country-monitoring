package org.wcm.usecase

import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.wcm.domain.api.PopulationAdapter
import org.wcm.domain.model.Population
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class PopulationUseCaseTest {

    private val adapter = mock<PopulationAdapter>()
    private val useCase = PopulationUseCase(adapter)
    private val date = LocalDate.of(2025, 12, 1)

    @Test
    fun `delegates population lookup by country`() {
        val populations = listOf(
            Population(population = 100.0, countryCode = "RUS", date = date)
        )
        whenever(adapter.getByCountryCode("RUS")).thenReturn(populations)

        assertEquals(populations, useCase.findAllByCountryCode("RUS"))
        verify(adapter).getByCountryCode("RUS")
    }

    @Test
    fun `delegates population lookup by date`() {
        val populations = listOf(
            Population(population = 100.0, countryCode = "RUS", date = date)
        )
        whenever(adapter.getAllCountriesByDate(date)).thenReturn(populations)

        assertEquals(populations, useCase.findAllCountriesByDate(date))
        verify(adapter).getAllCountriesByDate(date)
    }
}
