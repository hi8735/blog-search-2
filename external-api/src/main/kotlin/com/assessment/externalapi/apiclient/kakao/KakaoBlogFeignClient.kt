package com.assessment.externalapi.apiclient.kakao

import com.assessment.externalapi.apiclient.model.BlogSearchResult
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "kakao-api", url = "https://dapi.kakao.com")
interface KakaoBlogFeignClient {
    @GetMapping("/v2/search/blog")
    fun searchBlogs(
        @RequestHeader("Authorization") authorization: String,
        @RequestParam("query") query: String,
        @RequestParam("sort") sort: String? = null,
        @RequestParam("page") page: Int? = null,
        @RequestParam("size") size: Int? = null
    ): BlogSearchResult
}
