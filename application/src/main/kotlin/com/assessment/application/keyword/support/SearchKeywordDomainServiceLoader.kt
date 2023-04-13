package com.assessment.application.keyword.support

import com.assessment.domain.keyword.SearchKeywordRepository
import com.assessment.domain.keyword.SearchKeywordService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SearchKeywordDomainServiceLoader {
    @Bean
    fun searchKeywordService(searchKeywordRepository: SearchKeywordRepository): SearchKeywordService {
        return SearchKeywordService(searchKeywordRepository)
    }
}