package org.wcm.repository.mapper

import org.springframework.stereotype.Component
import org.wcm.domain.model.Feedback
import org.wcm.repository.entity.FeedbackEntity
import java.time.LocalDateTime

@Component
class FeedbackMapper {

    fun toDomain(entity: FeedbackEntity) =
        Feedback(
            id = entity.id,
            email = entity.email,
            message = entity.message,
            createdAt = entity.createdAt
        )

    fun toEntity(domain: Feedback) =
        FeedbackEntity(
            id = domain.id,
            email = domain.email,
            message = domain.message,
            createdAt = domain.createdAt ?: LocalDateTime.now()
        )
}
