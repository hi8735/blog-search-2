package com.assessment.blog

import com.assessment.blog.model.BlogSearchRequest
import com.assessment.blog.model.BlogSearchResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/search")
class BlogSearchController {

    @GetMapping("/blog")
    fun searchBlogs(blogSearchRequest: BlogSearchRequest): BlogSearchResponse {
        return BlogSearchResponse(emptyList(), 0, 0, true)
    }
}
