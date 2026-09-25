package org.wcm.repository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "feedback")
@Entity
data class FeedbackEntity(
    @Id
    @SequenceGenerator(
        name = "FeedbackEntity.idSequence",
        sequenceName = "feedback_id_seq",
        allocationSize = 1,
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "FeedbackEntity.idSequence",
    )
    val id: Long? = null,
    val email: String,
    val message: String,
    @Column(name = "created_at")
    val createdAt: LocalDateTime? = null,
)
