package org.wcm.repository.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "visit_counter")
@Entity
data class VisitCounterEntity(
    @Id
    val id: Long,
    val total: Long,
)
