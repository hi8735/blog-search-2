package com.assessment.externalapi.apiclient.blog.naver.model

import com.assessment.model.BlogSort

class NaverBlogSort(blogSort: BlogSort) {
    val sort = when (blogSort) {
        BlogSort.ACCURACY -> "sim"
        BlogSort.RECENCY -> "date"
    }
}
