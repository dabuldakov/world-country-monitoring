package org.wcm.repository.mapper

import org.springframework.stereotype.Component
import org.wcm.repository.entity.InternationalReserveEntity
import org.wcm.domain.model.InternationalReserve

@Component
class InternationalReserveMapper {

    fun toDomain(internationalReserveEntity: InternationalReserveEntity): InternationalReserve {
        return InternationalReserve(
            id = internationalReserveEntity.id,
            amount = internationalReserveEntity.amount,
            foreignExchange = internationalReserveEntity.foreignExchange,
            monetaryGold = internationalReserveEntity.monetaryGold,
            countryCode = internationalReserveEntity.countryCode,
            date = internationalReserveEntity.date
        )
    }
}