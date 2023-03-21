package com.assessment.externalapi.apiclient.blog.naver

import com.assessment.externalapi.apiclient.blog.naver.model.NaverBlogResult
import com.assessment.externalapi.apiclient.model.BlogSearchResult
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "naver-blog-api", url = "\${naver.blog.search.url}")
interface NaverBlogSearchFeignClient {
    @GetMapping("/v1/search/blog.json")
    fun searchBlogs(
        @RequestHeader("X-Naver-Client-Id") clientId: String,
        @RequestHeader("X-Naver-Client-Secret") clientSecret: String,
        @RequestParam("query") query: String,
        @RequestParam("sort") sort: String? = null,
        @RequestParam("start") start: Int? = null,
        @RequestParam("display") display: Int? = null
    ): NaverBlogResult
}
