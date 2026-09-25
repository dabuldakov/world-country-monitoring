package org.wcm.repository.adapter

import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.wcm.domain.model.Population
import org.wcm.repository.PopulationRepository
import org.wcm.repository.entity.PopulationEntity
import org.wcm.repository.mapper.PopulationMapper
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class PopulationAdapterImplTest {

    private val repository = mock<PopulationRepository>()
    private val mapper = PopulationMapper()
    private val adapter = PopulationAdapterImpl(repository, mapper)
    private val date = LocalDate.of(2025, 12, 1)

    @Test
    fun `loads population by country in repository order`() {
        val entities = listOf(
            PopulationEntity(id = 1L, population = 100.0, countryCode = "RUS", date = date),
            PopulationEntity(id = 2L, population = 200.0, countryCode = "RUS", date = date.plusYears(1))
        )
        whenever(repository.findAllByCountryCodeOrderByDate("RUS")).thenReturn(entities)

        val result = adapter.getByCountryCode("RUS")

        assertEquals(
            listOf(
                Population(id = 1L, population = 100.0, countryCode = "RUS", date = date),
                Population(id = 2L, population = 200.0, countryCode = "RUS", date = date.plusYears(1))
            ),
            result
        )
        verify(repository).findAllByCountryCodeOrderByDate("RUS")
    }

    @Test
    fun `loads all countries by date in repository order`() {
        val entities = listOf(
            PopulationEntity(id = 2L, population = 200.0, countryCode = "RUS", date = date),
            PopulationEntity(id = 1L, population = 100.0, countryCode = "USA", date = date)
        )
        whenever(repository.findAllByDateOrderByPopulation(date)).thenReturn(entities)

        val result = adapter.getAllCountriesByDate(date)

        assertEquals(listOf(2L, 1L), result.map { it.id })
        verify(repository).findAllByDateOrderByPopulation(date)
    }

    @Test
    fun `inserts a new population record`() {
        val population = Population(population = 100.0, countryCode = "RUS", date = date)
        whenever(repository.findFirstByCountryCodeAndDate("RUS", date)).thenReturn(null)
        whenever(repository.save(any<PopulationEntity>())).thenAnswer { it.getArgument(0) }

        adapter.saveAll(listOf(population))

        val saved = argumentCaptor<PopulationEntity>()
        verify(repository).save(saved.capture())
        assertEquals(
            PopulationEntity(population = 100.0, countryCode = "RUS", date = date),
            saved.firstValue
        )
    }

    @Test
    fun `updates an existing population record`() {
        val existing = PopulationEntity(id = 7L, population = 100.0, countryCode = "RUS", date = date)
        whenever(repository.findFirstByCountryCodeAndDate("RUS", date)).thenReturn(existing)
        whenever(repository.save(any<PopulationEntity>())).thenAnswer { it.getArgument(0) }

        adapter.saveAll(
            listOf(Population(population = 200.0, countryCode = "RUS", date = date))
        )

        val saved = argumentCaptor<PopulationEntity>()
        verify(repository).save(saved.capture())
        assertEquals(
            PopulationEntity(id = 7L, population = 200.0, countryCode = "RUS", date = date),
            saved.firstValue
        )
    }

    @Test
    fun `skips records without population value`() {
        adapter.saveAll(
            listOf(Population(population = null, countryCode = "RUS", date = date))
        )

        verify(repository, never()).findFirstByCountryCodeAndDate(any<String>(), any())
        verify(repository, never()).save(any<PopulationEntity>())
    }
}
