package org.wcm.repository

import org.wcm.repository.entity.InternationalReserveEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InternationalReserveRepository : JpaRepository<InternationalReserveEntity, Long> {

    fun findAllByCountryCodeOrderByDate(countryCode: String): List<InternationalReserveEntity>
}