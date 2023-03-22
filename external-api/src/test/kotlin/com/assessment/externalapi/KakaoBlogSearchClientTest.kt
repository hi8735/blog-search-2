package com.assessment.externalapi

import com.assessment.externalapi.apiclient.blog.kakao.model.KakaoBlogSearchResult
import com.assessment.externalapi.apiclient.blog.naver.model.NaverBlogSearchResult
import com.assessment.externalapi.apiclient.model.BlogSearchResult
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

class KakaoBlogSearchClientTest {

    private val objectMapper = ObjectMapper().registerKotlinModule()

    @Test
    fun `should map JSON data to KakaoBlogSearchResult`() {
        val jsonData = Files.readString(Paths.get("src/test/resources/kakao-blog-sample.json"))
        val kakaoBlogSearchResult: KakaoBlogSearchResult = objectMapper.readValue(jsonData)
        val blogSearchResult: BlogSearchResult = kakaoBlogSearchResult.toBlogSearchResult()

        assertThat(1281833).isEqualTo(kakaoBlogSearchResult.meta.totalCount)
        assertThat(2).isEqualTo(kakaoBlogSearchResult.documents.size)
        assertThat("캐나다 전역 ATM 수수료 면제 혜택이 기본 제공되는 <b>인터넷</b> <b>은행</b>, EQ Bank").isEqualTo(kakaoBlogSearchResult.documents[0].title)
        assertThat("제주<b>은행</b> <b>인터넷</b><b>은행</b> 불발, 주가는").isEqualTo(kakaoBlogSearchResult.documents[1].title)
        assertThat(2).isEqualTo(blogSearchResult.blogs.size)
    }

    @Test
    fun`should map JSON data to NaverBlogSearchResult`() {
        val jsonData = Files.readString(Paths.get("src/test/resources/naver-blog-sample.json"))
        val naverBlogSearchResult: NaverBlogSearchResult = objectMapper.readValue(jsonData)
        val blogSearchResult: BlogSearchResult = naverBlogSearchResult.toBlogSearchResult()

        assertThat(2421709).isEqualTo(naverBlogSearchResult.total)
        assertThat(2).isEqualTo(naverBlogSearchResult.items.size)
        assertThat("<b>인터넷은행</b>").isEqualTo(naverBlogSearchResult.items[0].title)
        assertThat("주택모기지 아파트담보대출 시중은행 <b>인터넷은행</b> 한도 금리비교").isEqualTo(naverBlogSearchResult.items[1].title)
        assertThat(2).isEqualTo(blogSearchResult.blogs.size)
    }
}
