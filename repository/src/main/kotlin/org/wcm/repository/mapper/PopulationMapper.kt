package org.wcm.repository.mapper

import org.springframework.stereotype.Component
import org.wcm.domain.model.Population
import org.wcm.repository.entity.PopulationEntity

@Component
class PopulationMapper {

    fun toDomain(entity: PopulationEntity) =
        Population(
            id = entity.id,
            population = entity.population,
            countryCode = entity.countryCode,
            date = entity.date
        )

    fun toEntity(domain: Population) =
        PopulationEntity(
            id = domain.id,
            population = domain.population,
            countryCode = domain.countryCode,
            date = domain.date
        )

    fun updatePopulation(entity: PopulationEntity, population: Double) =
        PopulationEntity(
            id = entity.id,
            population = population,
            countryCode = entity.countryCode,
            date = entity.date
        )
}
