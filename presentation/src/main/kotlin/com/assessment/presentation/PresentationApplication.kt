package com.assessment.presentation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.assessment.", "com.assessment.application", "com.assessment.domain", "com.assessment")
class PresentationApplication

fun main(args: Array<String>) {
    runApplication<PresentationApplication>(*args)
}
