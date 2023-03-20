package com.assessment.application.blog

import com.assessment.application.blog.event.BlogSearchedEvent
import com.assessment.application.blog.model.BlogSearchResponse
import com.assessment.externalapi.apiclient.BlogSearchClient
import com.assessment.externalapi.apiclient.kakao.SortBy
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class BlogSearchApplicationService(
    private val blogSearchClient: BlogSearchClient,
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    fun searchBlogs(
        query: String,
        sort: String = SortBy.RECENCY.name,
        page: Int = 1,
        size: Int = 10,
    ): BlogSearchResponse {
        val searchResult = blogSearchClient.searchBlogs(
            query = query,
            sort = sort,
            page = page,
            size = size
        )

        applicationEventPublisher.publishEvent(BlogSearchedEvent(query))

        return BlogSearchResponse.from(searchResult)
    }

    fun validateRequest() {

    }
}
