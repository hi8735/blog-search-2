package com.assessment.externalapi.apiclient.blog.kakao.model

import com.assessment.externalapi.apiclient.model.Blog
import com.assessment.externalapi.apiclient.model.BlogSearchResult
import com.assessment.externalapi.apiclient.model.PaginationInfo
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.format.DateTimeFormatter

data class KakaoBlogSearchResult(
    val meta: MetaData,
    val documents: List<Document>
) {
    fun toBlogSearchResult(): BlogSearchResult {
        val paginationInfo = PaginationInfo(
            totalCount = meta.totalCount,
            totalPages = meta.totalPages,
            hasNext = !meta.isEnd
        )

        val blogs = documents.map { document ->
            Blog(
                title = document.title,
                contents = document.contents,
                url = document.url,
                name = document.blogName,
                thumbnail = document.thumbnail,
                postedAt = document.dateTime
            )
        }

        return BlogSearchResult(
            paginationInfo = paginationInfo,
            blogs = blogs
        )
    }
}

data class MetaData(
    @JsonProperty("total_count")
    val totalCount: Int,
    @JsonProperty("pageable_count")
    val totalPages: Int,
    @JsonProperty("is_end")
    val isEnd: Boolean
)

data class Document(
    val title: String,
    val contents: String,
    val url: String,
    @JsonProperty("blogname")
    val blogName: String,
    val thumbnail: String,
    @JsonProperty("datetime")
    val dateTime: String
)
