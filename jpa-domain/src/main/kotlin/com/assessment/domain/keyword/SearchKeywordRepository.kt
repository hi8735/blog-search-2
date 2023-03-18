package com.assessment.domain.keyword

interface SearchKeywordRepository {
    fun save(searchKeyword: SearchKeyword): SearchKeyword
    fun findByKeyword(keyword: String): SearchKeyword?
    fun findTop10Keywords(): List<SearchKeyword>
}
