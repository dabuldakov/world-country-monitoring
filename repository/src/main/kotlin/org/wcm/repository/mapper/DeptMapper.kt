package org.wcm.repository.mapper

import org.springframework.stereotype.Component
import org.wcm.domain.model.Dept
import org.wcm.repository.entity.DeptEntity

@Component
class DeptMapper {
    fun toDomain(entity: DeptEntity) =
        Dept(
            id = entity.id,
            foreign = entity.foreign,
            countryCode = entity.countryCode,
            date = entity.date
        )
}