dependencies {
    implementation(project(":domain"))
    implementation(project(":usecase-api"))

    implementation("org.springframework.boot:spring-boot-starter-web:${property("springBootVersion")}")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${property("springDocVersion")}")
    implementation("org.springframework.boot:spring-boot-starter-validation:${property("springBootVersion")}")
}