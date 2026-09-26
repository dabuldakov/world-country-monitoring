package org.wcm.repository.mapper

import org.springframework.stereotype.Component
import org.wcm.domain.model.GrossDomesticProductPerCapita
import org.wcm.repository.entity.GrossDomesticProductPerCapitaEntity

@Component
class GrossDomesticProductPerCapitaMapper {

    fun toDomain(entity: GrossDomesticProductPerCapitaEntity) =
        GrossDomesticProductPerCapita(
            id = entity.id,
            amount = entity.amount,
            countryCode = entity.countryCode,
            date = entity.date
        )

    fun toEntity(domain: GrossDomesticProductPerCapita) =
        GrossDomesticProductPerCapitaEntity(
            id = domain.id,
            amount = domain.amount,
            countryCode = domain.countryCode,
            date = domain.date
        )

    fun updateAmount(entity: GrossDomesticProductPerCapitaEntity, amount: Double) =
        GrossDomesticProductPerCapitaEntity(
            id = entity.id,
            amount = amount,
            countryCode = entity.countryCode,
            date = entity.date
        )
}