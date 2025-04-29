package org.wcm.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.util.Date

@Table(name = "international_reserve")
@Entity
data class InternationalReserve(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val amount: Long,
    val foreignExchange: Long,
    val monetaryGold: Long,
    val country: Long,
    @CreationTimestamp
    val date: Date
)
