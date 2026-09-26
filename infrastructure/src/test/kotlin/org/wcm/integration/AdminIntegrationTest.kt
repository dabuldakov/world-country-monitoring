package org.wcm.integration

import com.jayway.jsonpath.JsonPath
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.wcm.domain.api.EmailSender
import org.wcm.domain.model.RefillFeature
import org.wcm.rest.client.worldbank.WorldBankClient
import org.wcm.rest.client.worldbank.model.WorldBankModel
import org.wcm.rest.client.worldbank.model.WorldBankValue
import org.wcm.usecase.api.RefreshJobApi
import kotlin.test.Test
import kotlin.test.assertNotEquals

class AdminIntegrationTest : AbstractDatabaseIntegrationTest() {

    @Autowired
    private lateinit var refreshJobApi: RefreshJobApi

    @MockBean
    private lateinit var emailSender: EmailSender

    @MockBean
    private lateinit var worldBankClient: WorldBankClient

    @Test
    fun `admin can login and read feedback, visits and refill result`() {
        val token = loginAsAdmin()

        mockMvc.perform(
            clientPost("/api/wcm/v0/feedback")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"email":"user@example.com","message":"Add more indicators"}""")
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.email").value("user@example.com"))

        mockMvc.perform(authorized(clientGet("/api/wcm/v0/admin/feedback"), token))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].email").value("user@example.com"))
            .andExpect(jsonPath("$[0].message").value("Add more indicators"))

        mockMvc.perform(clientPost("/api/wcm/v0/visits"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.count").value(1))

        mockMvc.perform(authorized(clientGet("/api/wcm/v0/admin/visits"), token))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.count").value(1))

        stubWorldBankClient()

        mockMvc.perform(authorized(clientPost("/api/wcm/v0/admin/refill/country/RUS"), token))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status").value("SUCCESS"))
            .andExpect(jsonPath("$.processedCount").value(1))

        mockMvc.perform(authorized(clientGet("/api/wcm/v0/admin/refill/last"), token))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.operation").value("RUS"))
            .andExpect(jsonPath("$.status").value("SUCCESS"))

        mockMvc.perform(authorized(clientGet("/api/wcm/v0/admin/refill/status"), token))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()").value(RefillFeature.entries.size))

        mockMvc.perform(authorized(clientPost("/api/wcm/v0/admin/refill/population/country/RUS"), token))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status").value("SUCCESS"))

        mockMvc.perform(authorized(clientPost("/api/wcm/v0/admin/refill/unknown/all"), token))
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `feature job records failure and retry queues failed country`() {
        val token = loginAsAdmin()
        doThrow(RuntimeException("world bank down"))
            .whenever(worldBankClient).getAllHistoryGDPbyCountry("RUS")

        val jobJson = mockMvc.perform(
            authorized(clientPost("/api/wcm/v0/admin/refill/jobs"), token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"feature":"gdp","countryCode":"RUS"}""")
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status").value("QUEUED"))
            .andReturn()
            .response
            .contentAsString
        val jobId = JsonPath.read<Number>(jobJson, "$.id").toLong()

        refreshJobApi.processNextJob()

        mockMvc.perform(authorized(clientGet("/api/wcm/v0/admin/refill/jobs/$jobId"), token))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status").value("PARTIAL"))
            .andExpect(jsonPath("$.failed").value(1))

        val retryJson = mockMvc.perform(
            authorized(clientPost("/api/wcm/v0/admin/refill/jobs/$jobId/retry"), token)
        )
            .andExpect(status().isOk)
            .andReturn()
            .response
            .contentAsString
        val retryId = JsonPath.read<Number>(retryJson, "$.id").toLong()

        assertNotEquals(jobId, retryId)
    }

    @Test
    fun `admin login rejects wrong password`() {
        mockMvc.perform(
            clientPost("/api/wcm/v0/admin/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"login":"admin","password":"wrong"}""")
        ).andExpect(status().isUnauthorized)
    }

    @Test
    fun `api requires frontend client header`() {
        mockMvc.perform(get("/api/wcm/v0/population/country/RUS"))
            .andExpect(status().isForbidden)
    }

    @Test
    fun `admin endpoints require bearer token`() {
        mockMvc.perform(clientGet("/api/wcm/v0/admin/visits"))
            .andExpect(status().isUnauthorized)
    }

    private fun stubWorldBankClient() {
        whenever(worldBankClient.getAllHistoryGDPbyCountry("RUS")).thenReturn(emptyResponse())
        whenever(worldBankClient.getAllHistoryPercentageDebtToGDPbyCountry("RUS")).thenReturn(emptyResponse())
        whenever(worldBankClient.getAllHistoryReservesAmountByCountry("RUS")).thenReturn(emptyResponse())
        whenever(worldBankClient.getAllHistoryPopulationByCountry("RUS")).thenReturn(
            WorldBankModel(
                count = 1,
                value = listOf(WorldBankValue(value = "143513328", year = "2025"))
            )
        )
        whenever(worldBankClient.getAllHistoryLifeExpectancyByCountry("RUS")).thenReturn(
            WorldBankModel(
                count = 1,
                value = listOf(WorldBankValue(value = "71.183415", year = "2020"))
            )
        )
    }

    private fun emptyResponse() = WorldBankModel(count = 0, value = emptyList())
}
