package com.assessment.application.events

import com.assessment.application.blog.event.BlogSearchedEvent
import com.assessment.application.keyword.SearchKeywordApplicationService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class SearchKeywordEventListener(
    private val searchKeywordService: SearchKeywordApplicationService
) {

    @EventListener
    fun handleBlogSearchedEvent(event: BlogSearchedEvent) {
        searchKeywordService.countKeyword(event.query)
    }
}
