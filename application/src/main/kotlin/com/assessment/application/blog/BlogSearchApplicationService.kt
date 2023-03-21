package com.assessment.application.blog

import com.assessment.application.blog.event.BlogSearchedEvent
import com.assessment.application.blog.model.BlogSearchResponse
import com.assessment.exception.ExternalApiException
import com.assessment.externalapi.apiclient.BlogSearchClient
import com.assessment.externalapi.apiclient.blog.kakao.KakaoBlogSearchClient
import com.assessment.externalapi.apiclient.blog.naver.NaverBlogSearchClient
import com.assessment.externalapi.apiclient.model.BlogSearchResult
import com.assessment.model.BlogSort
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

@Service
@Validated
class BlogSearchApplicationService(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val blogSearchCachedService: BlogSearchCachedService
) {
    fun searchBlogs(
        query: String,
        sort: BlogSort = BlogSort.ACCURACY,
        page: Int = 1,
        size: Int = 10,
    ): BlogSearchResponse {
        val searchResult = blogSearchCachedService.getBlogSearchResult(query = query, sort = sort, page = page, size = size)

        applicationEventPublisher.publishEvent(BlogSearchedEvent(query))

        return BlogSearchResponse.from(searchResult)
    }
}

