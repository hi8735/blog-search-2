package com.assessment.application.events

import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("dev")
class SQSEventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher
): BlogEventPublisher {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun publishEvent(event: Any) {
        log.info("dev event published")
        
        applicationEventPublisher.publishEvent(event)
    }
}
