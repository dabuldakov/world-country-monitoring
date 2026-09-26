package org.wcm.repository.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.LocalDate

@Table(name = "gross_domestic_product_per_capita")
@Entity
data class GrossDomesticProductPerCapitaEntity(
    @Id
    @SequenceGenerator(
        name = "GrossDomesticProductPerCapitaEntity.idSequence",
        sequenceName = "gross_domestic_product_per_capita_id_seq",
        allocationSize = 1,
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "GrossDomesticProductPerCapitaEntity.idSequence",
    )
    val id: Long? = null,
    val amount: Double? = null,
    val countryCode: String,
    val date: LocalDate,
)