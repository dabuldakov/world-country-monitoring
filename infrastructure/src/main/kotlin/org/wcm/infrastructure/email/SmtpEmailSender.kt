package org.wcm.infrastructure.email

import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component
import org.wcm.domain.api.EmailSender
import org.wcm.domain.model.Feedback

@Component
class SmtpEmailSender(
    private val mailSender: JavaMailSender,
    @Value("\${application.admin.email:dabuldakov@mail.ru}") private val adminEmail: String,
    @Value("\${spring.mail.username:}") private val fromEmail: String
) : EmailSender {

    override fun sendFeedbackNotification(feedback: Feedback) {
        val message = SimpleMailMessage()
        message.setTo(adminEmail)
        message.setFrom(fromEmail.ifBlank { adminEmail })
        message.setSubject("Новый отзыв с сайта мониторинга стран")
        message.setText(
            """
            Новый отзыв с сайта.

            Email: ${feedback.email}

            Сообщение:
            ${feedback.message}

            Дата: ${feedback.createdAt}
            """.trimIndent()
        )
        mailSender.send(message)
    }
}
