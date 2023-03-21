package com.assessment.externalapi.apiclient.model

import java.io.Serializable
data class BlogSearchResult (
    val paginationInfo: PaginationInfo,
    val blogs: List<Blog>
) : Serializable

data class PaginationInfo(
    val totalCount: Int,
    val totalPages: Int,
    val hasNext: Boolean
) : Serializable

data class Blog(
    val title: String,
    val contents: String,
    val url: String,
    val name: String,
    val thumbnail: String,
    val postedAt: String
) : Serializable
