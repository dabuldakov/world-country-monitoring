package org.wcm.controller.exception

import org.springdoc.api.ErrorMessage
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.wcm.domain.exception.EntityNotFoundException


@ControllerAdvice
class ControllerExceptionHandler {
    @ExceptionHandler(value = [EntityNotFoundException::class])
    fun resourceNotFoundException(ex: Exception, request: WebRequest): ResponseEntity<ErrorMessage> {
        val message = ErrorMessage(ex.message)
        return ResponseEntity(message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = [Exception::class])
    fun unexpectedException(ex: Exception, request: WebRequest): ResponseEntity<ErrorMessage> {
        val message = ErrorMessage(ex.message)
        return ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}