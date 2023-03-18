package com.assessment.blog.model

data class BlogSearchRequest(
    val query: String,
    val sort: String? = null,
    val page: Int? = null,
    val size: Int? = null
)
