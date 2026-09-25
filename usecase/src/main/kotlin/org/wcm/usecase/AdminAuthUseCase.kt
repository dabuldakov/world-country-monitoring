package org.wcm.usecase

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.wcm.usecase.api.AdminAuthApi
import java.security.MessageDigest
import java.util.Base64
import java.util.concurrent.TimeUnit
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

@Component
class AdminAuthUseCase(
    @Value("\${application.admin.login:admin}") private val adminLogin: String,
    @Value("\${application.admin.password:change-me}") private val adminPassword: String,
    @Value("\${application.admin.token-secret:change-me-too}") private val tokenSecret: String,
    @Value("\${application.admin.token-ttl-minutes:720}") private val tokenTtlMinutes: Long
) : AdminAuthApi {

    override fun login(login: String, password: String): String? {
        if (adminLogin.isBlank() || adminPassword.isBlank()) {
            return null
        }
        if (!constantTimeEquals(login, adminLogin) || !constantTimeEquals(password, adminPassword)) {
            return null
        }

        val expiresAt = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(tokenTtlMinutes)
        val payload = "$adminLogin:$expiresAt"
        return "$payload:${sign(payload)}"
    }

    override fun isValid(token: String?): Boolean {
        val parts = token?.split(":") ?: return false
        if (parts.size != 3) {
            return false
        }

        val login = parts[0]
        val expiresAt = parts[1].toLongOrNull() ?: return false
        val signature = parts[2]
        if (expiresAt < System.currentTimeMillis()) {
            return false
        }

        return constantTimeEquals(sign("$login:$expiresAt"), signature)
    }

    private fun sign(payload: String): String {
        val mac = Mac.getInstance(HMAC_ALGORITHM)
        mac.init(SecretKeySpec(tokenSecret.toByteArray(), HMAC_ALGORITHM))
        return Base64.getUrlEncoder().withoutPadding().encodeToString(mac.doFinal(payload.toByteArray()))
    }

    private fun constantTimeEquals(left: String, right: String): Boolean =
        MessageDigest.isEqual(left.toByteArray(), right.toByteArray())

    private companion object {
        const val HMAC_ALGORITHM = "HmacSHA256"
    }
}
