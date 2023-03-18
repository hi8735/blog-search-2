package com.assessment.application.blog.model

import com.assessment.externalapi.apiclient.model.BlogSearchResult
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


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
        val dateTime: ZonedDateTime
    )

    companion object {
        fun from(blogSearchResult: BlogSearchResult): BlogSearchResponse {
            val formatter: DateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

            return BlogSearchResponse(
                documents = blogSearchResult.documents.map { document ->
                    Document(
                        title = document.title,
                        contents = document.contents,
                        url = document.url,
                        blogName = document.blogName,
                        thumbnail = document.thumbnail,
                        dateTime = ZonedDateTime.parse(document.dateTime, formatter)
                    )
                },
                totalCount = blogSearchResult.meta.totalCount,
                totalPages = blogSearchResult.meta.totalPages,
                hasNext = blogSearchResult.meta.isEnd.not()
            )
        }
    }
}
