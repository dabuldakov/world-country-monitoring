package org.wcm.repository.mapper

import org.wcm.domain.model.Population
import org.wcm.repository.entity.PopulationEntity
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class PopulationMapperTest {

    private val mapper = PopulationMapper()
    private val date = LocalDate.of(2025, 12, 1)

    @Test
    fun `maps entity to domain`() {
        val entity = PopulationEntity(
            id = 42L,
            population = 143513328.0,
            countryCode = "RUS",
            date = date
        )

        assertEquals(
            Population(
                id = 42L,
                population = 143513328.0,
                countryCode = "RUS",
                date = date
            ),
            mapper.toDomain(entity)
        )
    }

    @Test
    fun `maps domain to entity`() {
        val population = Population(
            id = 42L,
            population = 143513328.0,
            countryCode = "RUS",
            date = date
        )

        assertEquals(
            PopulationEntity(
                id = 42L,
                population = 143513328.0,
                countryCode = "RUS",
                date = date
            ),
            mapper.toEntity(population)
        )
    }

    @Test
    fun `updates only population value`() {
        val entity = PopulationEntity(
            id = 42L,
            population = 100.0,
            countryCode = "RUS",
            date = date
        )

        assertEquals(
            PopulationEntity(
                id = 42L,
                population = 200.0,
                countryCode = "RUS",
                date = date
            ),
            mapper.updatePopulation(entity, 200.0)
        )
    }
}
