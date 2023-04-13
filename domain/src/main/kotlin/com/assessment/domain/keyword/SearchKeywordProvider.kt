package com.assessment.domain.keyword

class SearchKeywordProvider(
    private val searchKeywordRepository: SearchKeywordRepository
) {
    fun getPopularKeywords(): List<SearchKeyword> {
        return searchKeywordRepository.findPopularKeywords()
    }
}