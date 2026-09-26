package org.wcm.repository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.Instant

@Table(name = "refresh_job")
@Entity
data class RefreshJobEntity(
    @Id
    @SequenceGenerator(
        name = "RefreshJobEntity.idSequence",
        sequenceName = "refresh_job_id_seq",
        allocationSize = 1,
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "RefreshJobEntity.idSequence",
    )
    val id: Long? = null,
    val feature: String,
    @Column(name = "country_code")
    val countryCode: String? = null,
    val status: String,
    val total: Int = 0,
    val processed: Int = 0,
    val failed: Int = 0,
    @Column(name = "created_at")
    val createdAt: Instant? = null,
    @Column(name = "started_at")
    val startedAt: Instant? = null,
    @Column(name = "finished_at")
    val finishedAt: Instant? = null,
    @Column(name = "error_message")
    val errorMessage: String? = null,
)