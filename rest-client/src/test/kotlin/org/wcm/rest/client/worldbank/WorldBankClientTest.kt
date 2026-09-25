package org.wcm.rest.client.worldbank

import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.web.client.RestTemplate
import org.wcm.rest.client.worldbank.model.WorldBankModel
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class WorldBankClientTest {

    private val restTemplate = mock<RestTemplate>()
    private val client = WorldBankClient(restTemplate)

    @Test
    fun `requests population history from Data360`() {
        val response = WorldBankModel(count = 0, value = emptyList())
        whenever(restTemplate.getForObject(any<String>(), eq(WorldBankModel::class.java)))
            .thenReturn(response)

        val result = client.getAllHistoryPopulationByCountry("RUS")

        val url = argumentCaptor<String>()
        verify(restTemplate).getForObject(url.capture(), eq(WorldBankModel::class.java))
        assertEquals(response, result)
        assertTrue(url.firstValue.contains("INDICATOR=WB_WDI_SP_POP_TOTL"))
        assertTrue(url.firstValue.contains("REF_AREA=RUS"))
    }

    @Test
    fun `returns null when population request fails`() {
        whenever(restTemplate.getForObject(any<String>(), eq(WorldBankModel::class.java)))
            .thenThrow(RuntimeException("request failed"))

        assertNull(client.getAllHistoryPopulationByCountry("RUS"))
    }
}
