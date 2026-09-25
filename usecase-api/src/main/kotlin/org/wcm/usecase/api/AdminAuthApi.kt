package org.wcm.usecase.api

interface AdminAuthApi {

    fun login(login: String, password: String): String?

    fun isValid(token: String?): Boolean
}
