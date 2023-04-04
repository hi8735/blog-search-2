package com.assessment.presentation

import com.assessment.application.blog.BlogSearchApplicationService
import com.assessment.application.blog.model.BlogSearchResponse
import com.assessment.presentation.blog.BlogSearchController
import org.mockito.kotlin.whenever
import com.assessment.model.BlogSort
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

//@ExtendWith(SpringExtension::class)
//@WebMvcTest(controllers = [BlogSearchController::class])
@SpringBootTest()
@AutoConfigureMockMvc
class BlogSearchControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var blogSearchApplicationService: BlogSearchApplicationService

    private val testQuery = "인터넷 은행 만세"
    private val testSort = BlogSort.ACCURACY
    private val testPage = 1
    private val testSize = 10
    lateinit var searchResponse: BlogSearchResponse

    @BeforeEach
    fun setUp() {
        searchResponse = BlogSearchResponse(
            blogs = listOf(BlogSearchResponse.Blog(title = "test", name = "name", contents = "content", url = "url", postedAt = "20230322", thumbnail = "thumbnail")),
            paginationInfo = BlogSearchResponse.PaginationInfo(
                hasNext = false,
                totalPages = 0,
                totalCount = 0
            )
        )
    }

    @Test
    fun `should search blogs with valid request`() {
        whenever(blogSearchApplicationService.searchBlogs(testQuery, testSort, testPage, testSize)).thenReturn(searchResponse)

        mockMvc.perform(
            get("/api/v1/search/blog")
                .param("query", testQuery)
                .param("sort", testSort.name)
                .param("page", testPage.toString())
                .param("size", testSize.toString())
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.data.blogs").isNotEmpty)
            .andExpect(jsonPath("$.data.blogs[0].title").value("test"))
    }

    @Test
    fun `should return VaildationFail when query is empty`() {
        mockMvc.perform(
            get("/api/v1/search/blog")
                .param("query", "")
                .param("sort", "ACCURACY")
                .param("page", "1")
                .param("size", "10")
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.error.name").value("INVALID_REQUEST"))
    }

    @Test
    fun `should return VaildationFail when page is lower than 1`() {
        mockMvc.perform(
            get("/api/v1/search/blog")
                .param("query", "")
                .param("sort", "ACCURACY")
                .param("page", "1")
                .param("size", "10")
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.error.name").value("INVALID_REQUEST"))
    }

    @Test
    fun `should return errors with multiple validation errors`() {
        mockMvc.perform(
            get("/api/v1/search/blog")
                .param("query", "")
                .param("sort", "ACCURACY")
                .param("page", "0")
                .param("size", "0")
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.error.details", Matchers.hasSize<Any>(3) ))
    }
}
