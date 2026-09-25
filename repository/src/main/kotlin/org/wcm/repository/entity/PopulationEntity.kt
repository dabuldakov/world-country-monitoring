package org.wcm.repository.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.LocalDate

@Table(name = "population")
@Entity
data class PopulationEntity(
    @Id
    @SequenceGenerator(
        name = "PopulationEntity.idSequence",
        sequenceName = "population_id_seq",
        allocationSize = 1,
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "PopulationEntity.idSequence",
    )
    val id: Long? = null,
    val population: Double? = null,
    val countryCode: String,
    val date: LocalDate,
)
