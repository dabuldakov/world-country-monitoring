package org.wcm.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.wcm.repository.entity.GrossDomesticProductEntity
import java.time.LocalDate

@Repository
interface GrossDomesticProductRepository: JpaRepository<GrossDomesticProductEntity, Long> {

    fun findAllByCountryCodeOrderByDate(countryCode: String): List<GrossDomesticProductEntity>
    fun findFirstByCountryCodeAndDate(countryCode: String, date: LocalDate): GrossDomesticProductEntity?
    fun findAllByDateOrderByCurrent(date: LocalDate): List<GrossDomesticProductEntity>
}