plugins {
    kotlin("plugin.spring")
    id("org.springframework.boot")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":controller"))
    implementation(project(":controller-scheduler"))
    implementation(project(":rest-client"))
    implementation(project(":repository"))
    implementation(project(":usecase"))
    implementation(project(":usecase-api"))


    implementation("org.springframework.data:spring-data-jpa:${property("springBootVersion")}")
    implementation("org.springframework.boot:spring-boot-starter:${property("springBootVersion")}")
    implementation("org.springframework.boot:spring-boot-starter-actuator:${property("springBootVersion")}")
    implementation("org.springframework.boot:spring-boot-starter-mail:${property("springBootVersion")}")
}

dependencies {
    testImplementation("org.testcontainers:postgresql:${property("testContainersVersion")}")
    testImplementation("org.testcontainers:junit-jupiter:${property("testContainersVersion")}")
    testImplementation("org.springframework.boot:spring-boot-starter-test:${property("springBootVersion")}")
    testImplementation("org.springframework.boot:spring-boot-starter-web:${property("springBootVersion")}")
}

tasks.test {
    systemProperty("api.version", System.getenv("DOCKER_API_VERSION") ?: "1.44")
}