package com.assessment.application

import com.assessment.application.blog.BlogSearchApplicationService
import com.assessment.application.blog.BlogSearchCachedService
import com.assessment.application.events.BlogEventPublisher
import com.assessment.externalapi.apiclient.model.BlogSearchResult
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
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class BlogSearchApplicationServiceTest {

    @InjectMocks
    private lateinit var blogSearchApplicationService: BlogSearchApplicationService

    @Mock
    private lateinit var blogSearchCachedService: BlogSearchCachedService

    @Mock
    private lateinit var blogEventPublisher: BlogEventPublisher

    @BeforeEach
    fun setUp() {
        val blogSearchResult = BlogSearchResult(
            blogs = emptyList(),
            paginationInfo = PaginationInfo(
                hasNext = false,
                totalPages = 0,
                totalCount = 0
            )
        )

        whenever(blogSearchCachedService.getBlogSearchResult(any(), any(), any(), any())).thenReturn(blogSearchResult)
    }

    @Test
    fun `searchBlogs should publish BlogSearchEvent`() {
        blogSearchApplicationService.searchBlogs("test")

        verify(blogEventPublisher, times(1)).publishEvent(any())
    }
}
