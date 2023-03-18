package com.assessment.application.keyword.event

import com.assessment.application.blog.event.BlogSearchedEvent
import com.assessment.application.keyword.SearchKeywordService
import com.assessment.domain.keyword.SearchKeyword
import com.assessment.domain.keyword.SearchKeywordRepository
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class SearchKeywordEventListener(
    private val searchKeywordRepository: SearchKeywordRepository,
    private val searchKeywordService: SearchKeywordService
) {

    @EventListener
    fun handleBlogSearchedEvent(event: BlogSearchedEvent) {
        searchKeywordService.countKeyword(event.query)
    }
}
