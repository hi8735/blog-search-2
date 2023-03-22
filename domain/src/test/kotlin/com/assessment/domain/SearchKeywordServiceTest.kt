package com.assessment.domain

import com.assessment.domain.keyword.SearchKeyword
import com.assessment.domain.keyword.SearchKeywordRepository
import com.assessment.domain.keyword.SearchKeywordService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.argThat
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class SearchKeywordServiceTest {

    @InjectMocks
    private lateinit var searchKeywordService: SearchKeywordService

    @Mock
    private lateinit var searchKeywordRepository: SearchKeywordRepository

    private val testKeyword = "인터넷 은행"

    @BeforeEach
    fun setUp() {
        whenever(searchKeywordRepository.findByKeyword(testKeyword))
            .thenReturn(null)
    }

    @Test
    fun `countKeyword should call findByKeyword and save`() {
        // Given
        val searchKeyword = SearchKeyword.create(keyword = testKeyword)

        // When
        searchKeywordService.countKeyword(testKeyword)

        // Then
        verify(searchKeywordRepository).findByKeyword(testKeyword)
        verify(searchKeywordRepository).save(argThat { this.keyword == searchKeyword.keyword })
    }

    @Test
    fun `countKeyword should increment count when keyword exists`() {
        // Given
        val existingSearchKeyword = SearchKeyword.create(keyword = testKeyword).searched()

        whenever(searchKeywordRepository.findByKeyword(testKeyword))
            .thenReturn(existingSearchKeyword)

        // When
        searchKeywordService.countKeyword(testKeyword)

        // Then
        verify(searchKeywordRepository).findByKeyword(testKeyword)
        verify(searchKeywordRepository).save(argThat { this.count == existingSearchKeyword.count + 1 })
    }
}
