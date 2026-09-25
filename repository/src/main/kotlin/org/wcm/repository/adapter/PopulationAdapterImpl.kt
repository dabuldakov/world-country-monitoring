package org.wcm.repository.adapter

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.wcm.domain.api.PopulationAdapter
import org.wcm.domain.model.Population
import org.wcm.repository.PopulationRepository
import org.wcm.repository.mapper.PopulationMapper
import java.time.LocalDate

@Component
class PopulationAdapterImpl(
    private val repository: PopulationRepository,
    private val mapper: PopulationMapper
) : PopulationAdapter {

    @Transactional(readOnly = true)
    override fun getByCountryCode(countryCode: String): List<Population> {
        return repository.findAllByCountryCodeOrderByDate(countryCode).map { mapper.toDomain(it) }
    }

    @Transactional(readOnly = true)
    override fun getAllCountriesByDate(date: LocalDate): List<Population> {
        return repository.findAllByDateOrderByPopulation(date).map { mapper.toDomain(it) }
    }

    override fun saveAll(populations: List<Population>) {
        populations.forEach { population ->
            if (population.population != null) {
                repository.findFirstByCountryCodeAndDate(population.countryCode, population.date)?.let { entity ->
                    repository.save(mapper.updatePopulation(entity, population.population!!))
                } ?: repository.save(mapper.toEntity(population))
            }
        }
    }
}
