package com.assessment.externalapi.apiclient.blog.naver.model

import com.assessment.externalapi.apiclient.model.Blog
import com.assessment.externalapi.apiclient.model.BlogSearchResult
import com.assessment.externalapi.apiclient.model.PaginationInfo
import com.fasterxml.jackson.annotation.JsonProperty

data class NaverBlogSearchResult(
    @JsonProperty("lastBuildDate")
    val lastBuildDate: String,
    @JsonProperty("total")
    val total: Int,
    @JsonProperty("start")
    val start: Int,
    @JsonProperty("display")
    val display: Int,
    @JsonProperty("items")
    val items: List<NaverBlogItem>
) {
    fun toBlogSearchResult() = BlogSearchResult(
        paginationInfo = PaginationInfo(
            totalCount = total,
            totalPages = display,
            hasNext = (start + display) < total
        ),
        blogs = items.map {
            Blog(
                title = it.title,
                contents = it.description,
                url = it.link,
                name = it.bloggerName,
                thumbnail = "",
                postedAt = it.postDate
            )
        }
    )
}

data class NaverBlogItem(
    @JsonProperty("title")
    val title: String,
    @JsonProperty("link")
    val link: String,
    @JsonProperty("description")
    val description: String,
    @JsonProperty("bloggername")
    val bloggerName: String,
    @JsonProperty("bloggerlink")
    val bloggerLink: String,
    @JsonProperty("postdate")
    val postDate: String
)
