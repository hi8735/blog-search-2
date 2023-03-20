package com.assessment.externalapi.apiclient

import com.assessment.externalapi.apiclient.kakao.KakaoBlogSearchClient
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ActiveProfiles("test")
class ApiClientTest {
    @Autowired
    private lateinit var kakaoBlogSearchClient: KakaoBlogSearchClient

    @Test
    fun apiTest() {
        val result = kakaoBlogSearchClient.searchBlogs("코로나", "accuracy", 1, 50)
        println(result)
    }
}
