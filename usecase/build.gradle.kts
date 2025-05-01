dependencies {
    implementation(project(":domain"))
    implementation(project(":usecase-api"))
    implementation("org.springframework:spring-context:${property("springCoreVersion")}")
}