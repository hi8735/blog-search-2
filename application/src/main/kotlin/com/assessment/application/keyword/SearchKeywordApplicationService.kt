package com.assessment.application.keyword

import com.assessment.domain.keyword.SearchKeywordService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SearchKeywordApplicationService (
    private val searchKeywordService: SearchKeywordService
) {
    fun countKeyword(keyword: String) {
        searchKeywordService.countKeyword(keyword)
    }
}
