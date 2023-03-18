package com.assessment.externalapi.apiclient.model

import com.fasterxml.jackson.annotation.JsonProperty

data class BlogSearchResult(
    val meta: MetaData,
    val documents: List<Document>
)

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
