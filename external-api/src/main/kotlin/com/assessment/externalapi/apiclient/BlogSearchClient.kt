package com.assessment.externalapi.apiclient

import com.assessment.externalapi.apiclient.model.BlogSearchResult

interface BlogSearchClient {
    fun searchBlogs(query: String, sort: String, page: Int, size: Int): BlogSearchResult
}
