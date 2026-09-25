package org.wcm.repository.mapper

import org.springframework.stereotype.Component
import org.wcm.domain.model.VisitStatistics
import org.wcm.repository.entity.VisitCounterEntity

@Component
class VisitMapper {

    fun toDomain(entity: VisitCounterEntity) = VisitStatistics(count = entity.total)
}
