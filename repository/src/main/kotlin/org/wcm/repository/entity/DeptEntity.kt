package org.wcm.repository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.LocalDate

@Table(name = "dept")
@Entity
data class DeptEntity(
    @Id
    @SequenceGenerator(
        name = "DeptEntity.idSequence",
        sequenceName = "dept_id_seq",
        allocationSize = 1,
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "DeptEntity.idSequence",
    )
    val id: Long? = null,
    @Column(name = "foreign_amount")
    val foreign: Double? = null,
    val countryCode: String,
    val date: LocalDate
)
