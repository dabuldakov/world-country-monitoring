package org.wcm.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.wcm.repository.entity.GrossDomesticProductPerCapitaEntity
import java.time.LocalDate

@Repository
interface GrossDomesticProductPerCapitaRepository : JpaRepository<GrossDomesticProductPerCapitaEntity, Long> {

    fun findAllByCountryCodeOrderByDate(countryCode: String): List<GrossDomesticProductPerCapitaEntity>

    fun findFirstByCountryCodeAndDate(countryCode: String, date: LocalDate): GrossDomesticProductPerCapitaEntity?

    fun findAllByDateOrderByAmount(date: LocalDate): List<GrossDomesticProductPerCapitaEntity>
}