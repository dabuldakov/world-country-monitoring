package org.wcm.integration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.wcm.repository.PopulationRepository
import org.wcm.repository.entity.PopulationEntity
import java.time.LocalDate
import kotlin.test.Test

class PopulationApiIntegrationTest : AbstractDatabaseIntegrationTest() {

    @Autowired
    private lateinit var repository: PopulationRepository

    @Test
    fun `returns population history for a country`() {
        repository.save(
            PopulationEntity(
                population = 145245148.0,
                countryCode = "RUS",
                date = LocalDate.of(2020, 12, 1)
            )
        )
        repository.save(
            PopulationEntity(
                population = 146000000.0,
                countryCode = "RUS",
                date = LocalDate.of(2021, 12, 1)
            )
        )
        repository.save(
            PopulationEntity(
                population = 330000000.0,
                countryCode = "USA",
                date = LocalDate.of(2020, 12, 1)
            )
        )

        mockMvc.perform(get("/api/wcm/v0/population/country/RUS"))
            .andExpect(status().isOk)
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$[1]").exists())
            .andExpect(jsonPath("$[2]").doesNotExist())
            .andExpect(jsonPath("$[0].countryCode").value("RUS"))
            .andExpect(jsonPath("$[0].date").value("2020-12-01"))
            .andExpect(jsonPath("$[0].population").value(145245148.0))
            .andExpect(jsonPath("$[1].date").value("2021-12-01"))
    }

    @Test
    fun `returns all countries for a year sorted by population`() {
        repository.save(
            PopulationEntity(
                population = 330000000.0,
                countryCode = "USA",
                date = LocalDate.of(2022, 12, 1)
            )
        )
        repository.save(
            PopulationEntity(
                population = 145000000.0,
                countryCode = "RUS",
                date = LocalDate.of(2022, 12, 1)
            )
        )

        mockMvc.perform(get("/api/wcm/v0/population/year/2022"))
            .andExpect(status().isOk)
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$[1]").exists())
            .andExpect(jsonPath("$[2]").doesNotExist())
            .andExpect(jsonPath("$[0].countryCode").value("RUS"))
            .andExpect(jsonPath("$[1].countryCode").value("USA"))
            .andExpect(jsonPath("$[0].date").value("2022-12-01"))
    }

    @Test
    fun `returns an empty list for an unknown country`() {
        mockMvc.perform(get("/api/wcm/v0/population/country/XXX"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0]").doesNotExist())
    }
}
