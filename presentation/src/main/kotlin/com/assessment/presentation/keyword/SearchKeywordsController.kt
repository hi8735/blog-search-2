package com.assessment.presentation.keyword

import com.assessment.application.keyword.SearchKeywordApplicationProvider
import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/search")
@Api(description = "검색 키워드 API")
class SearchKeywordsController(
    private val searchKeywordApplicationProvider: SearchKeywordApplicationProvider
) {

    @GetMapping("/popular-keywords")
    fun getPopularKeywords(): List<Any> {
        return searchKeywordApplicationProvider.getPopularKeywords()
    }
}
