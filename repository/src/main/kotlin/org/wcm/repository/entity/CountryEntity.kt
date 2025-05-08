package org.wcm.repository.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "country")
@Entity
data class CountryEntity(
    @Id
    val code: String,
    val name: String
)
