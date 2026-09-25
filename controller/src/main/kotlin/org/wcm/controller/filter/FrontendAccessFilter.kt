package org.wcm.controller.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.wcm.usecase.api.AdminAuthApi

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class FrontendAccessFilter(
    @Value("\${application.frontend.enabled:true}") private val enabled: Boolean,
    @Value("\${application.frontend.client-key:wcm-frontend}") private val clientKey: String,
    private val adminAuthApi: AdminAuthApi
) : OncePerRequestFilter() {

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        if (!enabled) {
            return true
        }
        return request.method == OPTIONS_METHOD || !request.requestURI.startsWith(API_PREFIX)
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (request.getHeader(CLIENT_HEADER) != clientKey) {
            reject(response, HttpStatus.FORBIDDEN, "forbidden")
            return
        }

        val path = request.requestURI
        if (path.startsWith(ADMIN_PREFIX) && path != ADMIN_LOGIN_PATH) {
            val token = request.getHeader(HttpHeaders.AUTHORIZATION)?.removePrefix(BEARER_PREFIX)?.trim()
            if (!adminAuthApi.isValid(token)) {
                reject(response, HttpStatus.UNAUTHORIZED, "unauthorized")
                return
            }
        }

        filterChain.doFilter(request, response)
    }

    private fun reject(response: HttpServletResponse, status: HttpStatus, message: String) {
        response.status = status.value()
        response.contentType = "application/json"
        response.writer.write("""{"error":"$message"}""")
    }

    companion object {
        const val CLIENT_HEADER = "X-WCM-Client"

        private const val API_PREFIX = "/api/wcm/"
        private const val ADMIN_PREFIX = "/api/wcm/v0/admin/"
        private const val ADMIN_LOGIN_PATH = "/api/wcm/v0/admin/login"
        private const val BEARER_PREFIX = "Bearer "
        private const val OPTIONS_METHOD = "OPTIONS"
    }
}
