package com.assessment.application.keyword

import com.assessment.domain.keyword.SearchKeywordRepository
import com.assessment.domain.keyword.SearchKeywordService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SearchKeywordApplicationService (
    searchKeywordRepository: SearchKeywordRepository
) {
    private val searchKeywordService = SearchKeywordService(searchKeywordRepository)
    fun countKeyword(keyword: String) {
        searchKeywordService.countKeyword(keyword)
    }
}
