package com.assessment.application.blog.model

import com.assessment.externalapi.apiclient.model.BlogSearchResult

data class BlogSearchResponse (
    val paginationInfo: PaginationInfo,
    val blogs: List<Blog>,
) {
    data class PaginationInfo(
        val totalCount: Int,
        val totalPages: Int,
        val hasNext: Boolean
    )

    data class Blog(
        val title: String,
        val contents: String,
        val url: String,
        val name: String,
        val thumbnail: String,
        val postedAt: String
    )

    companion object {
        fun from(blogSearchResult: BlogSearchResult): BlogSearchResponse {
            return BlogSearchResponse(
                paginationInfo = PaginationInfo(
                    totalCount = blogSearchResult.paginationInfo.totalCount,
                    totalPages = blogSearchResult.paginationInfo.totalPages,
                    hasNext = blogSearchResult.paginationInfo.hasNext
                ),
                blogs = blogSearchResult.blogs.map { blog ->
                    Blog(
                        title = blog.title,
                        contents = blog.contents,
                        url = blog.url,
                        name = blog.name,
                        thumbnail = blog.thumbnail,
                        postedAt = blog.postedAt
                    )
                },
            )
        }
    }
}
