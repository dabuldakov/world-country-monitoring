package org.wcm.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.wcm.repository.entity.RefillCountryStatusEntity

@Repository
interface RefillCountryStatusRepository : JpaRepository<RefillCountryStatusEntity, Long> {

    fun findFirstByFeatureAndCountryCode(feature: String, countryCode: String): RefillCountryStatusEntity?

    fun findAllByFeature(feature: String): List<RefillCountryStatusEntity>
}