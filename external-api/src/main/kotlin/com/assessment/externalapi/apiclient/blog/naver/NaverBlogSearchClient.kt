package com.assessment.externalapi.apiclient.blog.naver

import com.assessment.exception.ExternalApiException
import com.assessment.externalapi.apiclient.BlogSearchClient
import com.assessment.externalapi.apiclient.blog.naver.model.NaverBlogSort
import com.assessment.externalapi.apiclient.model.BlogSearchResult
import com.assessment.model.BlogSort
import feign.FeignException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class NaverBlogSearchClient(
    private val naverBlogSearchFeignClient: NaverBlogSearchFeignClient,
    @Value("\${naver.client-id}")
    private val clientId: String,
    @Value("\${naver.client-secret}")
    private val clientSecret: String,
) : BlogSearchClient {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun searchBlogs(query: String, sort: BlogSort, page: Int, size: Int): BlogSearchResult {
        log.info("kakaoBlog search requested: query=$query, sort=$sort, page=$page, size=$size")

        return try {
            naverBlogSearchFeignClient.searchBlogs(
                clientId = clientId,
                clientSecret = clientSecret,
                query = query,
                sort = NaverBlogSort(sort).sort,
                start = page + 1,
                display = size,
            ).toBlogSearchResult()
        } catch (e: FeignException) {
            throw ExternalApiException(errorMessage = "네이버 블로그 검색 오류", cause = e)
        }
    }
}
