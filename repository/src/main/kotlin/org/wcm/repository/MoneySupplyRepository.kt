package org.wcm.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.wcm.repository.entity.MoneySupplyEntity
import java.time.LocalDate

@Repository
interface MoneySupplyRepository : JpaRepository<MoneySupplyEntity, Long> {
    fun findAllByCountryCodeOrderByDate(countryCode: String): List<MoneySupplyEntity>
    fun findAllByDateOrderByAmountUsd(date: LocalDate): List<MoneySupplyEntity>
}