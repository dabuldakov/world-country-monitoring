package org.wcm.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.wcm.repository.entity.CountryEntity

@Repository
interface CountryRepository : JpaRepository<CountryEntity, String> {

    fun findAllByOrderByNameAsc(): List<CountryEntity>
}