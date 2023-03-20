package com.assessment.domain.keyword

class SearchKeyword private constructor(
    val id: Long,
    val keyword: String,
    val count: Long
) {
    fun searched(): SearchKeyword {
        return this.copy(count = this.count + 1)
    }

    private fun copy(id: Long = this.id, keyword: String = this.keyword, count: Long = this.count): SearchKeyword {
        return SearchKeyword(id = id, keyword = keyword, count = count)
    }

    companion object {
        fun create(id: Long = 0, keyword: String, count: Long = 1): SearchKeyword {
            return SearchKeyword(id = id, keyword = keyword, count = count)
        }
    }
}