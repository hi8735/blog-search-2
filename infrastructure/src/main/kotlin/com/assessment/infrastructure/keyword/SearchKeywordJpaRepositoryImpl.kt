package com.assessment.infrastructure.keyword

import com.assessment.domain.keyword.SearchKeyword
import com.assessment.domain.keyword.SearchKeywordRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class SearchKeywordJpaRepositoryImpl: SearchKeywordRepository {
//    @Autowired
//    lateinit var searchKeywordJpaRepository: SearchKeywordJpaRepository

    override fun save(searchKeyword: SearchKeyword): SearchKeyword {
        //TODO: Implement this method
        return searchKeyword
    // return searchKeywordJpaRepository.save(SearchKeywordEntity.fromDomainModel(searchKeyword)).toDomainModel()
    }

    override fun findByKeyword(keyword: String): SearchKeyword? {
        return null
//        return searchKeywordJpaRepository.findByKeyword(keyword)?.toDomainModel()
    }

    override fun findPopularKeywords(): List<SearchKeyword> {
        return listOf()
//        return searchKeywordJpaRepository.findTop10ByOrderByCountDesc().map { it.toDomainModel() }
    }
}
