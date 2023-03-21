package com.assessment.externalapi.apiclient

import com.assessment.externalapi.apiclient.model.BlogSearchResult
import com.assessment.model.BlogSort

interface BlogSearchClient {
    fun searchBlogs(query: String, sort: BlogSort, page: Int, size: Int): BlogSearchResult
}
