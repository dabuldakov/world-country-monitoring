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
            date = entity.date,
            countryCode = entity.countryCode
        )
}