package com.assessment.domain.keyword

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class SearchKeyword private constructor(
   val keyword: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
    var count: Long = 0
    protected set

    fun searched() {
        count++
    }

    companion object {
        fun create(keyword: String): SearchKeyword {
            return SearchKeyword(keyword)
        }
    }
}
