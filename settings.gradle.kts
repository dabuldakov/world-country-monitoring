pluginManagement {

    plugins {
        val kotlinVersion: String by settings
        val kotlinterVersion: String by settings
        val springBootVersion: String by settings
        val avroPluginVersion: String by settings
        kotlin("jvm") version kotlinVersion
        kotlin("kapt") version kotlinVersion
        kotlin("plugin.jpa") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        id("org.springframework.boot") version springBootVersion
    }


    repositories {
        maven { url = uri("https://nexus.gts.rus.socgen/repository/maven-public")}
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
        gradlePluginPortal()
    }
}

rootProject.name = "world-country-monitoring"
include("controller")
include("domain")
include("infrastructure")
include("repository")
include("usecase")
include("usecase-api")
