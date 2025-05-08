dependencies {
    implementation(project(":domain"))

    implementation("org.springframework:spring-context:${property("springCoreVersion")}")
    implementation("org.springframework.boot:spring-boot-starter-web:${property("springBootVersion")}")
}
