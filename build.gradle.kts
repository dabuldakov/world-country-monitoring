val jvmVersion = 21
val javaVersion = JavaVersion.VERSION_21

plugins {
    kotlin("jvm")
    kotlin("kapt")
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.kapt")

    java.sourceCompatibility = javaVersion

    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter:${property("springBootVersion")}")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        testImplementation("org.mockito.kotlin:mockito-kotlin:${property("mockitoKotlinVersion")}")
        testImplementation("org.mockito:mockito-core:${property("mockitoVersion")}")
        testImplementation("org.mockito:mockito-junit-jupiter:${property("mockitoVersion")}")
        testImplementation("org.jetbrains.kotlin:kotlin-test")
    }

    kotlin {
        jvmToolchain(jvmVersion)
        compilerOptions {
            freeCompilerArgs.addAll(
                "-Xjsr305=strict",
                "-Xjvm-default=all",
            )
        }
    }

    tasks.test {
        useJUnitPlatform()
    }
}