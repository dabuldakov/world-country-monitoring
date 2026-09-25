package org.wcm.usecase

import org.mockito.kotlin.any
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.wcm.domain.api.EmailSender
import org.wcm.domain.api.FeedbackAdapter
import org.wcm.domain.model.Feedback
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class FeedbackUseCaseTest {

    private val feedbackAdapter = mock<FeedbackAdapter>()
    private val emailSender = mock<EmailSender>()
    private val useCase = FeedbackUseCase(feedbackAdapter, emailSender)

    @Test
    fun `saves feedback and sends notification`() {
        whenever(feedbackAdapter.save(any())).thenAnswer { it.getArgument(0) }

        val result = useCase.create(" user@example.com ", " Add charts ")

        assertEquals("user@example.com", result.email)
        assertEquals("Add charts", result.message)
        verify(emailSender).sendFeedbackNotification(result)
    }

    @Test
    fun `keeps saved feedback when email fails`() {
        whenever(feedbackAdapter.save(any())).thenAnswer { it.getArgument(0) }
        doThrow(RuntimeException("smtp down")).whenever(emailSender).sendFeedbackNotification(any())

        val result = useCase.create("user@example.com", "Add charts")

        assertNotNull(result)
        verify(emailSender).sendFeedbackNotification(result)
    }

    @Test
    fun `returns stored feedback list`() {
        val stored = listOf(Feedback(email = "user@example.com", message = "Add charts"))
        whenever(feedbackAdapter.findAll()).thenReturn(stored)

        assertEquals(stored, useCase.findAll())
        verify(feedbackAdapter).findAll()
        verify(emailSender, never()).sendFeedbackNotification(any())
    }
}
