package com.assessment.domain.keyword

class SearchKeywordService(
    private val searchKeywordRepository: SearchKeywordRepository
) {
    fun countKeyword(keyword: String) {
        val searchKeyword = searchKeywordRepository.findByKeyword(keyword)
            ?: SearchKeyword.create(keyword = keyword)

        searchKeywordRepository.save(searchKeyword.searched())
    }
}
