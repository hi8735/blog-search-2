package com.assessment.application.blog.model

import com.assessment.externalapi.apiclient.kakao.SortBy

data class BlogSearchRequest(
    val query: String,
    val sort: SortBy = SortBy.Recency,
    val page: Int = 1,
    val size: Int = 10
)
