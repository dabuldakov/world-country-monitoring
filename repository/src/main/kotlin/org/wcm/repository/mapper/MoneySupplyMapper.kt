package org.wcm.repository.mapper

import org.springframework.stereotype.Component
import org.wcm.domain.model.MoneySupply
import org.wcm.repository.entity.MoneySupplyEntity

@Component
class MoneySupplyMapper {
    fun toDomain(entity: MoneySupplyEntity) = MoneySupply(
        id = entity.id,
        amountLocalCurrency = entity.amountLocalCurrency,
        amountUsd = entity.amountUsd,
        countryCode = entity.countryCode,
        date = entity.date
    )
}