package com.assessment.infrastructure.keyword

import org.springframework.data.jpa.repository.JpaRepository

interface ISearchKeywordJpaRepository: JpaRepository<SearchKeywordEntity, Long> {
    fun findByKeyword(keyword: String): SearchKeywordEntity?
    fun findTop10ByOrderByCountDesc(): List<SearchKeywordEntity>
}
