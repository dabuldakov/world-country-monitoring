package org.wcm.domain.api

import org.wcm.domain.model.Feedback

interface EmailSender {

    fun sendFeedbackNotification(feedback: Feedback)
}
