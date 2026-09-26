package org.wcm.controller.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
open class WebMVCConfiguration : WebMvcConfigurer {

    @Value("\${application.frontend.allowed-origins:http://localhost:3000,http://country-monitoring.com,https://country-monitoring.com,http://www.country-monitoring.com,https://www.country-monitoring.com,http://countrymonitoring.ru,https://countrymonitoring.ru,http://www.countrymonitoring.ru,https://www.countrymonitoring.ru}")
    private lateinit var allowedOrigins: String

    override fun addCorsMappings(registry: CorsRegistry) {
        val origins = allowedOrigins
            .split(",")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .toTypedArray()

        registry
            .addMapping("/**")
            .allowedOrigins(*origins)
            .allowedMethods("GET", "POST", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true)
    }
}