package org.wcm.integration

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import org.wcm.infrastructure.Application

@Testcontainers
@SpringBootTest(classes = [Application::class])
@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
abstract class AbstractDatabaseIntegrationTest {

    companion object {
        @JvmField
        @Container
        val postgres: PostgreSQLContainer<*> = PostgreSQLContainer(DockerImageName.parse("postgres:17-alpine"))
            .withDatabaseName("wcm_test")
            .withUsername("wcm_test")
            .withPassword("wcm_test")

        @JvmStatic
        @DynamicPropertySource
        fun registerProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url") { postgres.jdbcUrl }
            registry.add("spring.datasource.username") { postgres.username }
            registry.add("spring.datasource.password") { postgres.password }
            registry.add("spring.jpa.hibernate.ddl-auto") { "validate" }
            registry.add("spring.flyway.enabled") { "true" }
        }

        const val CLIENT_HEADER = "X-WCM-Client"
        const val CLIENT_KEY = "wcm-test-client"
    }

    @Autowired
    protected lateinit var jdbcTemplate: JdbcTemplate

    @Autowired
    protected lateinit var mockMvc: MockMvc

    @BeforeEach
    fun clearPopulation() {
        jdbcTemplate.update("TRUNCATE TABLE population RESTART IDENTITY")
        jdbcTemplate.update("TRUNCATE TABLE feedback RESTART IDENTITY")
        jdbcTemplate.update("UPDATE visit_counter SET total = 0 WHERE id = 1")
    }

    protected fun clientGet(url: String): MockHttpServletRequestBuilder =
        get(url).header(CLIENT_HEADER, CLIENT_KEY)

    protected fun clientPost(url: String): MockHttpServletRequestBuilder =
        post(url).header(CLIENT_HEADER, CLIENT_KEY)

    protected fun authorized(
        request: MockHttpServletRequestBuilder,
        token: String
    ): MockHttpServletRequestBuilder = request.header("Authorization", "Bearer $token")

    protected fun loginAsAdmin(): String {
        val response = mockMvc.perform(
            clientPost("/api/wcm/v0/admin/login")
                .contentType("application/json")
                .content("""{"login":"admin","password":"test-admin"}""")
        ).andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk)
            .andReturn()
            .response
            .contentAsString

        return com.jayway.jsonpath.JsonPath.read(response, "$.token")
    }
}
