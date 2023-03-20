package com.assessment.infrastructure.keyword

import com.assessment.domain.keyword.SearchKeyword
import com.assessment.domain.keyword.SearchKeywordRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class SearchKeywordJpaRepository(
    var repository: ISearchKeywordJpaRepository
) : SearchKeywordRepository {
    override fun save(searchKeyword: SearchKeyword): SearchKeyword {
        return repository.save(SearchKeywordEntity.fromDomainModel(searchKeyword)).toDomainModel()
    }

    override fun findByKeyword(keyword: String): SearchKeyword? {
        return repository.findByKeyword(keyword)?.toDomainModel()
    }

    override fun findPopularKeywords(): List<SearchKeyword> {
        return repository.findTop10ByOrderByCountDesc().map { it.toDomainModel() }
    }
}
