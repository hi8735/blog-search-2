package com.assessment.application.blog

import com.assessment.application.blog.event.BlogSearchedEvent
import com.assessment.application.blog.model.BlogSearchResponse
import com.assessment.application.events.BlogEventPublisher
import com.assessment.model.BlogSort
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

@Service
@Validated
class BlogSearchApplicationService(
    private val blogEventPublisher: BlogEventPublisher,
    private val blogSearchCachedService: BlogSearchCachedService
) {
    fun searchBlogs(
        query: String,
        sort: BlogSort = BlogSort.ACCURACY,
        page: Int = 1,
        size: Int = 10,
    ): BlogSearchResponse {
        val searchResult = blogSearchCachedService.getBlogSearchResult(
            query = query,
            sort = sort,
            page = page,
            size = size
        )

        blogEventPublisher.publishEvent(BlogSearchedEvent(query))

        return BlogSearchResponse.from(searchResult)
    }
}

