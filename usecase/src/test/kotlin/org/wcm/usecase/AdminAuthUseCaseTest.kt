package org.wcm.usecase

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class AdminAuthUseCaseTest {

    private val useCase = AdminAuthUseCase(
        adminLogin = "admin",
        adminPassword = "secret",
        tokenSecret = "test-secret",
        tokenTtlMinutes = 60
    )

    @Test
    fun `issues a valid token for valid credentials`() {
        val token = useCase.login("admin", "secret")

        assertNotNull(token)
        assertTrue(useCase.isValid(token))
    }

    @Test
    fun `rejects invalid credentials`() {
        assertNull(useCase.login("admin", "wrong"))
        assertNull(useCase.login("user", "secret"))
    }

    @Test
    fun `rejects expired token`() {
        val expired = AdminAuthUseCase(
            adminLogin = "admin",
            adminPassword = "secret",
            tokenSecret = "test-secret",
            tokenTtlMinutes = -1
        )

        val token = expired.login("admin", "secret")

        assertNotNull(token)
        assertFalse(useCase.isValid(token))
        assertFalse(useCase.isValid("not-a-token"))
        assertFalse(useCase.isValid(null))
    }

    @Test
    fun `uses configured admin login in token`() {
        val custom = AdminAuthUseCase("root", "pwd", "secret", 60)
        val token = custom.login("root", "pwd")

        assertNotNull(token)
        assertTrue(token.startsWith("root:"))
        assertEquals(3, token.split(":").size)
    }
}
