package org.wcm.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.wcm.repository.entity.PopulationEntity
import java.time.LocalDate

@Repository
interface PopulationRepository : JpaRepository<PopulationEntity, Long> {

    fun findAllByCountryCodeOrderByDate(countryCode: String): List<PopulationEntity>
    fun findFirstByCountryCodeAndDate(countryCode: String, date: LocalDate): PopulationEntity?
    fun findAllByDateOrderByPopulation(date: LocalDate): List<PopulationEntity>
}
