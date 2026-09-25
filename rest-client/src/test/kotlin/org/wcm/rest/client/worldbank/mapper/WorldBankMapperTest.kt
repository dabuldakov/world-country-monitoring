package org.wcm.rest.client.worldbank.mapper

import org.wcm.rest.client.worldbank.model.WorldBankModel
import org.wcm.rest.client.worldbank.model.WorldBankValue
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class WorldBankMapperTest {

    @Test
    fun `maps population observations to domain values`() {
        val model = WorldBankModel(
            count = 1,
            value = listOf(WorldBankValue(value = "143513328", year = "2025"))
        )

        val result = WorldBankMapper().toDomainPopulation(model, "RUS")

        assertEquals(1, result.size)
        assertEquals(143513328.0, result.single().population)
        assertEquals("RUS", result.single().countryCode)
        assertEquals(LocalDate.of(2025, 12, 1), result.single().date)
    }
}
