package org.wcm.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.wcm.repository.entity.RefreshJobEntity

@Repository
interface RefreshJobRepository : JpaRepository<RefreshJobEntity, Long> {

    @Query(
        value = "SELECT * FROM refresh_job WHERE status = 'QUEUED' ORDER BY id LIMIT 1 FOR UPDATE SKIP LOCKED",
        nativeQuery = true
    )
    fun claimNextQueued(): RefreshJobEntity?

    fun findFirstByFeatureAndCountryCodeAndStatusIn(
        feature: String,
        countryCode: String,
        statuses: List<String>
    ): RefreshJobEntity?

    fun findFirstByFeatureAndCountryCodeIsNullAndStatusIn(
        feature: String,
        statuses: List<String>
    ): RefreshJobEntity?

    fun findAllByOrderByIdDesc(): List<RefreshJobEntity>
}