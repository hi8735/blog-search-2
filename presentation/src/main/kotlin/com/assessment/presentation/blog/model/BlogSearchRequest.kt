package com.assessment.presentation.blog.model

import com.assessment.model.BlogSort
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Length
import org.springframework.validation.annotation.Validated
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@ApiModel
data class BlogSearchRequest(
    @field:Length(message = "검색어는 1자에서 1000자까지 입력 가능합니다.", max = 1000, min = 1)
    @NotBlank(message = "검색어는 필수 입력값입니다.")
    val query: String,
    val sort: BlogSort = BlogSort.ACCURACY,
    @field:Min(message = "page의 최소단위는 1 입니다.", value = 1)
    val page: Int = 1,
    @field:Max(message = "size는 최대 50까지 입력 가능합니다.", value = 50)
    @field:Min(message = "size는 최소단위는 1 입니다.", value = 1)
    val size: Int = 10
)
