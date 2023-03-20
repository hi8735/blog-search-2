package com.assessment.domain.keyword

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class SearchKeywordProvider(
    private val searchKeywordRepository: SearchKeywordRepository
) {
    fun getPopularKeywords(): List<SearchKeyword> {
        return searchKeywordRepository.findPopularKeywords()
    }
}