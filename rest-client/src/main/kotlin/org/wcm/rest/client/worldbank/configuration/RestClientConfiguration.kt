package org.wcm.rest.client.worldbank.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
open class RestClientConfiguration {

    @Bean
    open fun restTemplate(): RestTemplate {
        return RestTemplate()
    }
}