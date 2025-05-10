package org.wcm.repository.mapper

import org.springframework.stereotype.Component
import org.wcm.domain.model.Debt
import org.wcm.repository.entity.DebtEntity

@Component
class DebtMapper {
    fun toDomain(entity: DebtEntity) =
        Debt(
            id = entity.id,
            foreign = entity.foreign,
            percentageToGDP = entity.percentageToGDP,
            countryCode = entity.countryCode,
            date = entity.date
        )

    fun toEntity(domain: Debt) =
        DebtEntity(
            id = domain.id,
            foreign = domain.foreign,
            percentageToGDP = domain.percentageToGDP,
            countryCode = domain.countryCode,
            date = domain.date
        )

    fun updatePercentageToGDP(entity: DebtEntity, percentage: Double) =
        DebtEntity(
            id = entity.id,
            foreign = entity.foreign,
            percentageToGDP = percentage,
            countryCode = entity.countryCode,
            date = entity.date
        )
}