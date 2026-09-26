package org.wcm.repository.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.LocalDate

@Table(name = "life_expectancy")
@Entity
data class LifeExpectancyEntity(
    @Id
    @SequenceGenerator(
        name = "LifeExpectancyEntity.idSequence",
        sequenceName = "life_expectancy_id_seq",
        allocationSize = 1,
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "LifeExpectancyEntity.idSequence",
    )
    val id: Long? = null,
    val years: Double? = null,
    val countryCode: String,
    val date: LocalDate,
)