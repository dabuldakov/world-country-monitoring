package org.wcm.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.wcm.repository.entity.DebtEntity
import java.time.LocalDate

@Repository
interface DebtRepository : JpaRepository<DebtEntity, Long> {
    fun findAllByCountryCodeOrderByDate(countryCode: String): List<DebtEntity>
    fun findFirstByCountryCodeAndDate(countryCode: String, date: LocalDate): DebtEntity?
    fun findAllByDateOrderByPercentageToGDP(date: LocalDate): List<DebtEntity>
}