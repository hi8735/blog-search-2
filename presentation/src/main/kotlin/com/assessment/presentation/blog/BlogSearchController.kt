package com.assessment.presentation.blog

import com.assessment.application.blog.BlogSearchApplicationService
import com.assessment.application.blog.model.BlogSearchResponse
import com.assessment.exception.ErrorDetail
import com.assessment.exception.ValidationException
import com.assessment.presentation.blog.model.BlogSearchRequest
import io.swagger.annotations.Api
import org.slf4j.LoggerFactory
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/search")
@Api(description = "블로그 검색 API")
class BlogSearchController(
    private val blogSearchApplicationService: BlogSearchApplicationService,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/blog")
    fun searchBlogs(@Validated request: BlogSearchRequest, bindingResult: BindingResult): BlogSearchResponse {
        if (bindingResult.hasErrors()) {
            val errors = bindingResult.fieldErrors.map { ErrorDetail(field = it.field, value = it.rejectedValue.toString(), reason = it.defaultMessage.toString())}
            log.info("검색 요청 파라미터 오류: {}", errors)

            throw ValidationException(errors = errors)
        }

        return blogSearchApplicationService.searchBlogs(
            query = request.query,
            sort = request.sort,
            page = request.page,
            size = request.size
        )
    }
}
