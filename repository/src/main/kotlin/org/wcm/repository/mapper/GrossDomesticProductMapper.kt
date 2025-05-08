package org.wcm.repository.mapper

import org.springframework.stereotype.Component
import org.wcm.domain.model.GrossDomesticProduct
import org.wcm.repository.entity.GrossDomesticProductEntity

@Component
class GrossDomesticProductMapper {

    fun toDomain(entity: GrossDomesticProductEntity) =
        GrossDomesticProduct(
            id = entity.id,
            absolut = entity.absolut,
            purchasingPowerParities = entity.purchasingPowerParities,
            current = entity.current,
            date = entity.date,
            countryCode = entity.countryCode
        )

    fun toEntity(domain: GrossDomesticProduct) =
        GrossDomesticProductEntity(
            id = domain.id,
            absolut = domain.absolut,
            purchasingPowerParities = domain.purchasingPowerParities,
            current = domain.current,
            date = domain.date,
            countryCode = domain.countryCode
        )

    fun updateCurrent(entity: GrossDomesticProductEntity, current: Double) {
        GrossDomesticProductEntity(
            id = entity.id,
            absolut = entity.absolut,
            purchasingPowerParities = entity.purchasingPowerParities,
            current = current,
            date = entity.date,
            countryCode = entity.countryCode
        )
    }
}