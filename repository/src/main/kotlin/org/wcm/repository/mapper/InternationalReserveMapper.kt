package org.wcm.repository.mapper

import org.springframework.stereotype.Component
import org.wcm.domain.model.InternationalReserve
import org.wcm.repository.entity.InternationalReserveEntity

@Component
class InternationalReserveMapper {

    fun toDomain(entity: InternationalReserveEntity) =
        InternationalReserve(
            id = entity.id,
            amount = entity.amount,
            foreignExchange = entity.foreignExchange,
            monetaryGold = entity.monetaryGold,
            monetaryGoldTonn = entity.monetaryGoldTonn,
            countryCode = entity.countryCode,
            date = entity.date

        )


    fun toEntity(domain: InternationalReserve) =
        InternationalReserveEntity(
            id = domain.id,
            amount = domain.amount,
            foreignExchange = domain.foreignExchange,
            monetaryGold = domain.monetaryGold,
            monetaryGoldTonn = domain.monetaryGoldTonn,
            countryCode = domain.countryCode,
            date = domain.date
        )

    fun updateAmount(entity: InternationalReserveEntity, amount: Double) =
        InternationalReserveEntity(
            id = entity.id,
            amount = amount,
            foreignExchange = entity.foreignExchange,
            monetaryGold = entity.monetaryGold,
            monetaryGoldTonn = entity.monetaryGoldTonn,
            countryCode = entity.countryCode,
            date = entity.date
        )
}