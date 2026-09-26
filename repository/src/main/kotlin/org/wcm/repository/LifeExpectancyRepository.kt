package org.wcm.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.wcm.repository.entity.LifeExpectancyEntity
import java.time.LocalDate

@Repository
interface LifeExpectancyRepository : JpaRepository<LifeExpectancyEntity, Long> {

    fun findAllByCountryCodeOrderByDate(countryCode: String): List<LifeExpectancyEntity>

    fun findFirstByCountryCodeAndDate(countryCode: String, date: LocalDate): LifeExpectancyEntity?

    fun findAllByDateOrderByYears(date: LocalDate): List<LifeExpectancyEntity>
}