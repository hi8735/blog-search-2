package com.assessment.presentation.blog

import com.assessment.application.blog.BlogSearchApplicationService
import com.assessment.application.blog.model.BlogSearchResponse
import com.assessment.presentation.blog.model.BlogSearchRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/search")
class BlogSearchController(
    private val blogSearchApplicationService: BlogSearchApplicationService
) {

    @GetMapping("/blog")
    fun searchBlogs(request: BlogSearchRequest): BlogSearchResponse {
        return blogSearchApplicationService.searchBlogs(
            query = request.query,
            sort = request.sort,
            page = request.page,
            size = request.size
        )
    }
}
