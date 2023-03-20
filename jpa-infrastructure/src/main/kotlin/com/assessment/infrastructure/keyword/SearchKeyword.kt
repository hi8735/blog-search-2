package com.assessment.infrastructure.keyword

import com.assessment.domain.keyword.SearchKeyword
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "search_keywords")
class SearchKeywordEntity private constructor(
    searchKeyword: SearchKeyword
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = searchKeyword.id
    val keyword: String = searchKeyword.keyword
    val count: Long = searchKeyword.count

    fun toDomainModel(): SearchKeyword{
        return SearchKeyword.create(id = id, keyword = keyword, count = count)
    }

    companion object {
        fun fromDomainModel(searchKeyword: SearchKeyword): SearchKeywordEntity {
            return SearchKeywordEntity(searchKeyword)
        }
    }
}