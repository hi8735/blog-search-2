package com.assessment.presentation.blog.model

data class BlogSearchRequest(
//    @Valid
//    @Size(message = "검색어의 길이는 1000자 이하여야 합니다.", max = 5)
    val query: String,
    val sort: String = "recency",
//    @Min(0, message = "page는 0 이상이어야 합니다.")
    val page: Int = 1,
    val size: Int = 10
)
