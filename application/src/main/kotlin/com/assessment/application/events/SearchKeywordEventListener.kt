package com.assessment.application.events

import com.assessment.application.blog.event.BlogSearchedEvent
import com.assessment.application.keyword.SearchKeywordApplicationService
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class SearchKeywordEventListener(
    private val searchKeywordService: SearchKeywordApplicationService
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @EventListener
    fun handleBlogSearchedEvent(event: BlogSearchedEvent) {
        log.info("BlogSearchedEvent: {}", event)

        searchKeywordService.countKeyword(event.query)
    }
}
