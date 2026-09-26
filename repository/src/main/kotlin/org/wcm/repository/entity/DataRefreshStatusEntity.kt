package org.wcm.repository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "data_refresh_status")
@Entity
data class DataRefreshStatusEntity(
    @Id
    val feature: String,
    @Column(name = "last_updated_at")
    val lastUpdatedAtEpochMillis: Long? = null,
    val status: String? = null,
    @Column(name = "processed_count")
    val processedCount: Int? = null,
    @Column(name = "error_message")
    val errorMessage: String? = null,
)