package com.assessment.keyword

import com.assessment.keyword.model.PopularKeywordsResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/search")
class PopularKeywordsController {

    @GetMapping("/popular-keywords")
    fun getPopularKeywords(): List<PopularKeywordsResponse> {
        return emptyList()
    }
}
