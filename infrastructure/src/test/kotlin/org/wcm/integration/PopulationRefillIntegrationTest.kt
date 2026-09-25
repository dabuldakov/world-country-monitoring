package org.wcm.integration

import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.wcm.repository.PopulationRepository
import org.wcm.rest.client.worldbank.WorldBankClient
import org.wcm.rest.client.worldbank.model.WorldBankModel
import org.wcm.rest.client.worldbank.model.WorldBankValue
import kotlin.test.Test
import kotlin.test.assertEquals

class PopulationRefillIntegrationTest : AbstractDatabaseIntegrationTest() {

    @Autowired
    private lateinit var repository: PopulationRepository

    @MockBean
    private lateinit var worldBankClient: WorldBankClient

    @Test
    fun `scheduler stores population history and repeated refill updates existing rows`() {
        val populationResponse = WorldBankModel(
            count = 2,
            value = listOf(
                WorldBankValue(value = "145245148", year = "2020"),
                WorldBankValue(value = "146000000", year = "2021")
            )
        )
        whenever(worldBankClient.getAllHistoryGDPbyCountry("RUS")).thenReturn(emptyResponse())
        whenever(worldBankClient.getAllHistoryPercentageDebtToGDPbyCountry("RUS")).thenReturn(emptyResponse())
        whenever(worldBankClient.getAllHistoryReservesAmountByCountry("RUS")).thenReturn(emptyResponse())
        whenever(worldBankClient.getAllHistoryPopulationByCountry("RUS")).thenReturn(populationResponse)

        mockMvc.perform(get("/api/wcm/v0/scheduler/update/country/RUS"))
            .andExpect(status().isOk)
            .andExpect(content().string("OK"))

        var saved = repository.findAllByCountryCodeOrderByDate("RUS")
        assertEquals(2, saved.size)
        assertEquals(listOf(145245148.0, 146000000.0), saved.map { it.population })

        mockMvc.perform(get("/api/wcm/v0/scheduler/update/country/RUS"))
            .andExpect(status().isOk)

        saved = repository.findAllByCountryCodeOrderByDate("RUS")
        assertEquals(2, saved.size)
    }

    private fun emptyResponse() = WorldBankModel(count = 0, value = emptyList())
}
