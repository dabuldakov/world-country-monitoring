plugins {
    kotlin("plugin.jpa")
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":domain"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:${property("springBootVersion")}")
    runtimeOnly("org.flywaydb:flyway-core:${property("flywayVersion")}")
    runtimeOnly("org.postgresql:postgresql:${property("postgreSqlVersion")}")

}