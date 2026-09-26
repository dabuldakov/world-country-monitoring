package org.wcm.repository.mapper

import org.springframework.stereotype.Component
import org.wcm.domain.model.RefillFeatureStatus
import org.wcm.repository.entity.DataRefreshStatusEntity

@Component
class DataRefreshStatusMapper {

    fun toDomain(entity: DataRefreshStatusEntity) =
        RefillFeatureStatus(
            feature = entity.feature,
            lastUpdatedAtEpochMillis = entity.lastUpdatedAtEpochMillis,
            status = entity.status,
            processedCount = entity.processedCount,
            errorMessage = entity.errorMessage
        )

    fun toEntity(domain: RefillFeatureStatus) =
        DataRefreshStatusEntity(
            feature = domain.feature,
            lastUpdatedAtEpochMillis = domain.lastUpdatedAtEpochMillis,
            status = domain.status,
            processedCount = domain.processedCount,
            errorMessage = domain.errorMessage
        )
}