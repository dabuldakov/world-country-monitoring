package org.wcm.repository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Table(name = "data_refresh_country_status")
@Entity
data class RefillCountryStatusEntity(
    @Id
    @SequenceGenerator(
        name = "RefillCountryStatusEntity.idSequence",
        sequenceName = "data_refresh_country_status_id_seq",
        allocationSize = 1,
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "RefillCountryStatusEntity.idSequence",
    )
    val id: Long? = null,
    val feature: String,
    @Column(name = "country_code")
    val countryCode: String,
    @Column(name = "last_updated_at")
    val lastUpdatedAtEpochMillis: Long? = null,
    val status: String? = null,
    @Column(name = "error_message")
    val errorMessage: String? = null,
)