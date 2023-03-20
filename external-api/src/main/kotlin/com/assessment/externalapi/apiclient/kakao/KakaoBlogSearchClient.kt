package com.assessment.externalapi.apiclient.kakao

import com.assessment.externalapi.apiclient.BlogSearchClient
import com.assessment.externalapi.apiclient.common.KAKAO_AUTHORIZATION_PREFIX
import com.assessment.externalapi.apiclient.model.BlogSearchResult
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Component
class KakaoBlogSearchClient(
    private val kakaoBlogFeignClient: KakaoBlogFeignClient,
    @Value("\${kakao.api-key}")
    private val kakaoApiKey: String
) : BlogSearchClient {
    override fun searchBlogs(query: String, sort: String, page: Int, size: Int): BlogSearchResult {
        val authorization = "$KAKAO_AUTHORIZATION_PREFIX $kakaoApiKey"

        return kakaoBlogFeignClient.searchBlogs(
            authorization = authorization,
            query = query,
            sort = sort,
            page = page,
            size = size,
        )
    }
}
