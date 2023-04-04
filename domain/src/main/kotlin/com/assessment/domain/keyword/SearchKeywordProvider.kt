package com.assessment.domain.keyword

import org.springframework.stereotype.Service

@Service
class SearchKeywordProvider(
    private val searchKeywordRepository: SearchKeywordRepository
) {
    fun getPopularKeywords(): List<SearchKeyword> {
        return searchKeywordRepository.findPopularKeywords()
    }
}
