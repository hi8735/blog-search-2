package com.assessment.application.keyword.model

import com.assessment.domain.keyword.SearchKeyword

class SearchKeywordModel private constructor(
    val keyword: String,
    val count: Long
) {
    companion object {
        fun from(searchKeyword: SearchKeyword): SearchKeywordModel {
            return SearchKeywordModel(
                keyword = searchKeyword.keyword,
                count = searchKeyword.count
            )
        }
    }
}
