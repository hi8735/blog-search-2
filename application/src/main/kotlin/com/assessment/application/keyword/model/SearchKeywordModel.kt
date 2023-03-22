package com.assessment.application.keyword.model

import com.assessment.domain.keyword.SearchKeyword
import java.io.Serializable

class SearchKeywordModel private constructor(
    val keyword: String,
    val count: Long
) : Serializable {
    companion object {
        fun from(searchKeyword: SearchKeyword): SearchKeywordModel {
            return SearchKeywordModel(
                keyword = searchKeyword.keyword,
                count = searchKeyword.count
            )
        }
    }
}
