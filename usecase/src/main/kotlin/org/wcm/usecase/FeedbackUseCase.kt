package org.wcm.usecase

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.wcm.domain.api.EmailSender
import org.wcm.domain.api.FeedbackAdapter
import org.wcm.domain.model.Feedback
import org.wcm.usecase.api.FeedbackApi

@Component
class FeedbackUseCase(
    private val feedbackAdapter: FeedbackAdapter,
    private val emailSender: EmailSender
) : FeedbackApi {

    private val logger: Logger = LoggerFactory.getLogger(FeedbackUseCase::class.java)

    override fun create(email: String, message: String): Feedback {
        val feedback = feedbackAdapter.save(
            Feedback(
                email = email.trim(),
                message = message.trim()
            )
        )

        try {
            emailSender.sendFeedbackNotification(feedback)
        } catch (exception: Exception) {
            logger.error("Error while sending feedback notification", exception)
        }

        return feedback
    }

    override fun findAll(): List<Feedback> = feedbackAdapter.findAll()
}
