package org.wcm.controller.v0

import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.http.HttpStatus
import org.wcm.domain.model.Population
import org.wcm.usecase.api.PopulationApi
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class PopulationControllerTest {

    private val api = mock<PopulationApi>()
    private val controller = PopulationController(api)
    private val date = LocalDate.of(2025, 12, 1)

    @Test
    fun `returns population by country`() {
        val populations = listOf(
            Population(population = 143513328.0, countryCode = "RUS", date = date)
        )
        whenever(api.findAllByCountryCode("RUS")).thenReturn(populations)

        val response = controller.getByCountry("RUS")

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(populations, response.body)
        verify(api).findAllByCountryCode("RUS")
    }

    @Test
    fun `converts year to December first and returns population`() {
        val populations = listOf(
            Population(population = 100.0, countryCode = "RUS", date = date)
        )
        whenever(api.findAllCountriesByDate(date)).thenReturn(populations)

        val response = controller.getAllCountriesByDate("2025")

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(populations, response.body)
        verify(api).findAllCountriesByDate(date)
    }
}
