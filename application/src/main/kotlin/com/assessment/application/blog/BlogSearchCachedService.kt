package com.assessment.application.blog

import com.assessment.exception.ExternalApiException
import com.assessment.externalapi.apiclient.BlogSearchClient
import com.assessment.externalapi.apiclient.blog.kakao.KakaoBlogSearchClient
import com.assessment.externalapi.apiclient.blog.naver.NaverBlogSearchClient
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

    @Cacheable(cacheNames = ["test"], key = "#query + #sort + #page + #size")
    fun getBlogSearchResult(
        query: String,
        sort: BlogSort = BlogSort.RECENCY,
        page: Int = 1,
        size: Int = 10,
    ): BlogSearchResult {
        val kakaoBlogSearchClient = blogSearchClients.first { it is KakaoBlogSearchClient }
        return try {
            kakaoBlogSearchClient.searchBlogs(
                query = query,
                sort = sort,
                page = page,
                size = size
            )
        } catch (e: ExternalApiException) {
            log.error("카카오 블로그 검색 오류", e)

            val naverBlogSearchClient = blogSearchClients.first { it is NaverBlogSearchClient }

            naverBlogSearchClient.searchBlogs(
                query = query,
                sort = sort,
                page = page,
                size = size
            )
        }
    }
}
