package com.assessment.application.blog

import com.assessment.application.cache.CacheConstants.BLOG_SEARCH
import com.assessment.exception.ExternalApiException
import com.assessment.externalapi.apiclient.BlogSearchClient
import com.assessment.externalapi.apiclient.model.BlogSearchResult
import com.assessment.model.BlogSort
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class BlogSearchCachedService(
    private val blogSearchClients: List<BlogSearchClient>,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Cacheable(cacheNames = [BLOG_SEARCH], key = "#query + #sort + #page + #size")
    fun getBlogSearchResult(
        query: String,
        sort: BlogSort = BlogSort.RECENCY,
        page: Int = 1,
        size: Int = 10,
    ): BlogSearchResult {
        blogSearchClients.forEach { blogSearchClient ->
            try {
                return blogSearchClient.searchBlogs(
                    query = query,
                    sort = sort,
                    page = page,
                    size = size
                )
            } catch (e: ExternalApiException) {
                log.error("${blogSearchClient.javaClass.simpleName} 블로그 검색 오류", e)
            }
        }

        throw ExternalApiException(errorMessage = "블로그 검색에 살패하였습니다.")
    }
}
