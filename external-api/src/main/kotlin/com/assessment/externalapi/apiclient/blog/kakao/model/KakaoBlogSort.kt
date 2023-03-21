package com.assessment.externalapi.apiclient.blog.kakao.model

import com.assessment.model.BlogSort

class KakaoBlogSort(blogSort: BlogSort) {
    val sort = when (blogSort) {
        BlogSort.ACCURACY -> "accuracy"
        BlogSort.RECENCY -> "recency"
    }
}
