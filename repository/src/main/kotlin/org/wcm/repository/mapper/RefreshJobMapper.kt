package org.wcm.repository.mapper

import org.springframework.stereotype.Component
import org.wcm.domain.model.RefillCountryStatus
import org.wcm.domain.model.RefreshJob
import org.wcm.domain.model.RefreshJobItem
import org.wcm.repository.entity.RefillCountryStatusEntity
import org.wcm.repository.entity.RefreshJobEntity
import org.wcm.repository.entity.RefreshJobItemEntity

@Component
class RefreshJobMapper {

    fun toDomain(entity: RefreshJobEntity) =
        RefreshJob(
            id = entity.id,
            feature = entity.feature,
            countryCode = entity.countryCode,
            status = entity.status,
            total = entity.total,
            processed = entity.processed,
            failed = entity.failed,
            createdAt = entity.createdAt,
            startedAt = entity.startedAt,
            finishedAt = entity.finishedAt,
            errorMessage = entity.errorMessage
        )

    fun toEntity(domain: RefreshJob) =
        RefreshJobEntity(
            id = domain.id,
            feature = domain.feature,
            countryCode = domain.countryCode,
            status = domain.status,
            total = domain.total,
            processed = domain.processed,
            failed = domain.failed,
            createdAt = domain.createdAt,
            startedAt = domain.startedAt,
            finishedAt = domain.finishedAt,
            errorMessage = domain.errorMessage
        )

    fun toDomain(entity: RefreshJobItemEntity) =
        RefreshJobItem(
            id = entity.id,
            jobId = entity.jobId,
            countryCode = entity.countryCode,
            status = entity.status,
            errorMessage = entity.errorMessage,
            updatedAt = entity.updatedAt
        )

    fun toEntity(domain: RefreshJobItem) =
        RefreshJobItemEntity(
            id = domain.id,
            jobId = domain.jobId,
            countryCode = domain.countryCode,
            status = domain.status,
            errorMessage = domain.errorMessage,
            updatedAt = domain.updatedAt
        )

    fun toDomain(entity: RefillCountryStatusEntity) =
        RefillCountryStatus(
            feature = entity.feature,
            countryCode = entity.countryCode,
            lastUpdatedAtEpochMillis = entity.lastUpdatedAtEpochMillis,
            status = entity.status,
            errorMessage = entity.errorMessage
        )

    fun toEntity(domain: RefillCountryStatus, existing: RefillCountryStatusEntity? = null) =
        RefillCountryStatusEntity(
            id = existing?.id,
            feature = domain.feature,
            countryCode = domain.countryCode,
            lastUpdatedAtEpochMillis = domain.lastUpdatedAtEpochMillis,
            status = domain.status,
            errorMessage = domain.errorMessage
        )
}