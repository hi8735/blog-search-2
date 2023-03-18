package com.assessment.blog.model

import java.time.LocalDateTime

data class BlogSearchResponse(
    val documents: List<Document>,
    val totalCount: Int,
    val totalPages: Int,
    val hasNext: Boolean
) {
    data class Document(
        val title: String,
        val contents: String,
        val url: String,
        val blogName: String,
        val thumbnail: String,
        val dateTime: LocalDateTime
    )
}
