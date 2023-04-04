package com.assessment.externalapi.apiclient.blog.kakao

import com.assessment.exception.ExternalApiException
import com.assessment.externalapi.apiclient.BlogSearchClient
import com.assessment.externalapi.apiclient.blog.kakao.model.KakaoBlogSort
import com.assessment.externalapi.apiclient.common.KAKAO_AUTHORIZATION_PREFIX
import com.assessment.externalapi.apiclient.model.BlogSearchResult
import com.assessment.model.BlogSort
import feign.FeignException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AakaoBlogSearchClient(
    private val kakaoBlogSearchFeignClient: KakaoBlogSearchFeignClient,
    @Value("\${kakao.api-key}")
    private val kakaoApiKey: String
) : BlogSearchClient {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun searchBlogs(query: String, sort: BlogSort, page: Int, size: Int): BlogSearchResult {
        val authorization = "$KAKAO_AUTHORIZATION_PREFIX $kakaoApiKey"
        log.info("kakaoBlog search requested: query=$query, sort=$sort, page=$page, size=$size")

        return try {
            kakaoBlogSearchFeignClient.searchBlogs(
                authorization = authorization,
                query = query,
                sort = KakaoBlogSort(sort).sort,
                page = page,
                size = size,
            ).toBlogSearchResult()
        } catch (e: FeignException) {
            throw ExternalApiException(errorMessage = "카카오 블로그 검색 오류", cause = e)
        }
    }
}
