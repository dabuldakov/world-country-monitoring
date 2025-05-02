package org.wcm.infrastructure

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication(scanBasePackages = ["org.wcm"])
internal class Application

fun main(args: Array<String>) {
    //TimeZone.setDefault(TimeZone.getTimeZone(System.getenv("TZ") ?: "Europe/Moscow"))
    runApplication<Application>(*args)
}