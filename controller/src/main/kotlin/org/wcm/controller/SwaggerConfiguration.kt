package org.wcm.controller

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration("WorldCountryMonitoringSwaggerConfiguration")
@ConditionalOnProperty(name = ["application.swaggerApi"], havingValue = "true")
open class SwaggerConfiguration {
    @Bean
    open fun wcmGroupedOpenApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("WorldCountryMonitoring")
            .pathsToMatch(PathConstant.BASE + "/**")
            .displayName("World Country Monitoring")
            .build()
    }
}