package com.assessment.application.keyword

import com.assessment.application.keyword.model.SearchKeywordModel
import com.assessment.domain.keyword.SearchKeyword
import com.assessment.domain.keyword.SearchKeywordProvider
import com.assessment.domain.keyword.SearchKeywordService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class SearchKeywordApplicationProvider (
    private val searchKeywordProvider: SearchKeywordProvider
) {
    fun getPopularKeywords(): List<SearchKeywordModel> {
        return searchKeywordProvider.getPopularKeywords().map { SearchKeywordModel.from(it) }
    }
}
