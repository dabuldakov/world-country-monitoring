package org.wcm.repository.mapper

import org.springframework.stereotype.Component
import org.wcm.domain.model.LifeExpectancy
import org.wcm.repository.entity.LifeExpectancyEntity

@Component
class LifeExpectancyMapper {

    fun toDomain(entity: LifeExpectancyEntity) =
        LifeExpectancy(
            id = entity.id,
            years = entity.years,
            countryCode = entity.countryCode,
            date = entity.date
        )

    fun toEntity(domain: LifeExpectancy) =
        LifeExpectancyEntity(
            id = domain.id,
            years = domain.years,
            countryCode = domain.countryCode,
            date = domain.date
        )

    fun updateYears(entity: LifeExpectancyEntity, years: Double) =
        LifeExpectancyEntity(
            id = entity.id,
            years = years,
            countryCode = entity.countryCode,
            date = entity.date
        )
}