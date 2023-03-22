package com.assessment.application

import com.assessment.application.blog.BlogSearchCachedService
import com.assessment.externalapi.apiclient.BlogSearchClient
import com.assessment.externalapi.apiclient.blog.kakao.KakaoBlogSearchClient
import com.assessment.externalapi.apiclient.blog.naver.NaverBlogSearchClient
import com.assessment.externalapi.apiclient.model.BlogSearchResult
import com.assessment.exception.ExternalApiException
import com.assessment.externalapi.apiclient.model.PaginationInfo
import com.assessment.model.BlogSort
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.argThat
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class BlogSearchCachedServiceTest {

    @Mock
    private lateinit var kakaoBlogSearchClient: KakaoBlogSearchClient

    @Mock
    private lateinit var naverBlogSearchClient: NaverBlogSearchClient

    private lateinit var blogSearchCachedService: BlogSearchCachedService
    private lateinit var blogSearchResult: BlogSearchResult

    private val testQuery = "test"
    private val testSort = BlogSort.RECENCY
    private val testPage = 1
    private val testSize = 10

    @BeforeEach
    fun setUp() {
        blogSearchCachedService = BlogSearchCachedService(listOf(kakaoBlogSearchClient, naverBlogSearchClient))
        blogSearchResult = BlogSearchResult(
            blogs = emptyList(),
            paginationInfo = PaginationInfo(
                hasNext = false,
                totalPages = 0,
                totalCount = 0
            )
        )
    }

    @Test
    fun `call naver client api when kakao api fails`() {
        whenever(kakaoBlogSearchClient.searchBlogs(testQuery, testSort, testPage, testSize)).thenThrow(ExternalApiException("카카오 api 오류"))
        whenever(naverBlogSearchClient.searchBlogs(testQuery, testSort, testPage, testSize)).thenReturn(blogSearchResult)

        blogSearchCachedService.getBlogSearchResult(testQuery, testSort, testPage, testSize)

        verify(naverBlogSearchClient).searchBlogs(testQuery, testSort, testPage, testSize)
    }
}
