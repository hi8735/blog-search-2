package com.assessment.application.events

interface BlogEventPublisher {
    fun publishEvent(event: Any)
}
