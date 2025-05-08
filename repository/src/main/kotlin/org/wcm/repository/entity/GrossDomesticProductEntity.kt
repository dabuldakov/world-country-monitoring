package org.wcm.repository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.LocalDate

@Table(name = "gross_domestic_product")
@Entity
data class GrossDomesticProductEntity(
    @Id
    @SequenceGenerator(
        name = "GrossDomesticProductEntity.idSequence",
        sequenceName = "gross_domestic_product_id_seq",
        allocationSize = 1,
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "GrossDomesticProductEntity.idSequence",
    )
    val id: Long? = null,
    val absolut: Double? = null,
    val purchasingPowerParities: Double? = null,
    @Column(name = "current_amount")
    val current: Double? = null,
    val countryCode: String,
    val date: LocalDate,
)
