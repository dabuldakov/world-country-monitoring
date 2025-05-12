package org.wcm.repository.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.LocalDate

@Table(name = "international_reserve")
@Entity
data class InternationalReserveEntity(
    @Id
    @SequenceGenerator(
        name = "InternationalReserveEntity.idSequence",
        sequenceName = "international_reserve_id_seq",
        allocationSize = 1,
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "InternationalReserveEntity.idSequence",
    )
    val id: Long? = null,
    val amount: Double? = null,
    val foreignExchange: Double? = null,
    val monetaryGold: Double? = null,
    val monetaryGoldTonn: Double? = null,
    val countryCode: String,
    val date: LocalDate
)
