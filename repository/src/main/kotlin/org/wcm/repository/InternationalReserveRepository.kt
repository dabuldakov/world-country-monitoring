package org.wcm.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.wcm.repository.entity.InternationalReserveEntity
import java.time.LocalDate

@Repository
interface InternationalReserveRepository : JpaRepository<InternationalReserveEntity, Long> {

    fun findAllByCountryCodeOrderByDate(countryCode: String): List<InternationalReserveEntity>
    fun findFirstByCountryCodeAndDate(countryCode: String, date: LocalDate): InternationalReserveEntity?
}