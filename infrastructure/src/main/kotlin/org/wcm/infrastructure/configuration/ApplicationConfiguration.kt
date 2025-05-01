package org.wcm.infrastructure.configuration

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EntityScan(basePackages = ["org.wcm.repository"])
@EnableJpaRepositories(basePackages = ["org.wcm.repository"])
internal class ApplicationConfiguration
