package com.assessment.application.blog

import com.assessment.application.blog.event.BlogSearchedEvent
import com.assessment.application.blog.model.BlogSearchRequest
import com.assessment.application.blog.model.BlogSearchResponse
import com.assessment.externalapi.apiclient.BlogSearchClient
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class BlogSearchService(
    private val blogSearchClient: BlogSearchClient,
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    fun searchBlogs(request: BlogSearchRequest): BlogSearchResponse {
        val searchResult = blogSearchClient.searchBlogs(
            query = request.query,
            sort = request.sort.value,
            page = request.page,
            size = request.size
        )

        applicationEventPublisher.publishEvent(BlogSearchedEvent(request.query))

        return BlogSearchResponse.from(searchResult)
    }
}
