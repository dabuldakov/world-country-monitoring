pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
        gradlePluginPortal()
    }
}

rootProject.name = "world-country-monitoring"
include("controller")
include("domain")
include("repository")
