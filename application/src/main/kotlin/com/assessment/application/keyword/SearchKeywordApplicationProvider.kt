package com.assessment.application.keyword

import com.assessment.application.cache.CacheConstants.POPULAR_KEYWORDS
import com.assessment.application.keyword.model.SearchKeywordModel
import com.assessment.domain.keyword.SearchKeywordProvider
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class SearchKeywordApplicationProvider (
    private val searchKeywordProvider: SearchKeywordProvider
) {
    @Cacheable(cacheNames = [POPULAR_KEYWORDS])
    fun getPopularKeywords(): List<SearchKeywordModel> {
        return searchKeywordProvider.getPopularKeywords().map { SearchKeywordModel.from(it) }
    }
}
