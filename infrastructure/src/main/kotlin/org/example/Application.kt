package org.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
internal class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}