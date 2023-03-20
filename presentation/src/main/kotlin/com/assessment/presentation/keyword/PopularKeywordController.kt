package com.assessment.presentation.keyword

import com.assessment.application.keyword.SearchKeywordApplicationProvider
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/search")
class SearchKeywordsController(
    private val searchKeywordApplicationProvider: SearchKeywordApplicationProvider
) {

    @GetMapping("/popular-keywords")
    fun getPopularKeywords(): List<Any> {
        return searchKeywordApplicationProvider.getPopularKeywords()
    }
}
