package org.wcm.repository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.Instant

@Table(name = "refresh_job_item")
@Entity
data class RefreshJobItemEntity(
    @Id
    @SequenceGenerator(
        name = "RefreshJobItemEntity.idSequence",
        sequenceName = "refresh_job_item_id_seq",
        allocationSize = 1,
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "RefreshJobItemEntity.idSequence",
    )
    val id: Long? = null,
    @Column(name = "job_id")
    val jobId: Long,
    @Column(name = "country_code")
    val countryCode: String,
    val status: String,
    @Column(name = "error_message")
    val errorMessage: String? = null,
    @Column(name = "updated_at")
    val updatedAt: Instant? = null,
)