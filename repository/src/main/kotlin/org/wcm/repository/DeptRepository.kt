package org.wcm.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.wcm.repository.entity.DeptEntity

@Repository
interface DeptRepository: JpaRepository<DeptEntity, Long> {
    fun findAllByCountryCodeOrderByDate(countryCode: String): List<DeptEntity>
}