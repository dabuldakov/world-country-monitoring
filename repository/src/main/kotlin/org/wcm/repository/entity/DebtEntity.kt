package org.wcm.repository.entity

import jakarta.persistence.*
import java.time.LocalDate

@Table(name = "debt")
@Entity
data class DebtEntity(
    @Id
    @SequenceGenerator(
        name = "DebtEntity.idSequence",
        sequenceName = "debt_id_seq",
        allocationSize = 1,
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "DebtEntity.idSequence",
    )
    val id: Long? = null,
    @Column(name = "foreign_amount")
    val foreign: Double? = null,
    @Column(name = "percentage_to_gdp")
    val percentageToGDP: Double? = null,
    val countryCode: String,
    val date: LocalDate
)
