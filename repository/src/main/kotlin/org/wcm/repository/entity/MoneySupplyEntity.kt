package org.wcm.repository.entity

import jakarta.persistence.*
import java.time.LocalDate

@Table(name = "money_supply")
@Entity
data class MoneySupplyEntity(
    @Id
    @SequenceGenerator(
        name = "MoneySupplyEntity.idSequence",
        sequenceName = "money_supply_id_seq",
        allocationSize = 1,
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "MoneySupplyEntity.idSequence",
    )
    val id: Long? = null,
    val amountLocalCurrency: Double? = null,
    val amountUsd: Double? = null,
    val countryCode: String,
    val date: LocalDate,
)
