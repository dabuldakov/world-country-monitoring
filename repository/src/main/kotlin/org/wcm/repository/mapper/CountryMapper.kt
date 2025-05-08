package org.wcm.repository.mapper

import org.springframework.stereotype.Component
import org.wcm.domain.model.Country
import org.wcm.repository.entity.CountryEntity

@Component
class CountryMapper {

    fun toDomain(entity: CountryEntity): Country {
        return Country(entity.code, entity.name)
    }
}