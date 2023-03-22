package com.assessment.application.config

import com.assessment.application.cache.CacheConstants.BLOG_SEARCH
import com.assessment.application.cache.CacheConstants.BLOG_SEARCH_TTL_SECONDS
import com.assessment.application.cache.CacheConstants.POPULAR_KEYWORDS
import com.assessment.application.cache.CacheConstants.POPULAR_KEYWORDS_TTL_SECONDS
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.CacheKeyPrefix
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@Configuration
@EnableCaching
class CacheConfig {
    @Bean
    fun redisCacheManager(
        redisConnectionFactory: RedisConnectionFactory,
        configurationByCache: Map<String, RedisCacheConfiguration>
    ): CacheManager {
        return RedisCacheManager.RedisCacheManagerBuilder
            .fromConnectionFactory(redisConnectionFactory)
            .withInitialCacheConfigurations(configurationByCache)
            .cacheDefaults(defaultConfiguration())
            .build()
    }

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory()
    }

    @Bean
    fun configurationByCache(): Map<String, RedisCacheConfiguration> {
        return mapOf(
            BLOG_SEARCH to defaultConfiguration().entryTtl(
                Duration.ofSeconds(BLOG_SEARCH_TTL_SECONDS)
            ),
            POPULAR_KEYWORDS to defaultConfiguration().entryTtl(
                Duration.ofSeconds(POPULAR_KEYWORDS_TTL_SECONDS)
            )
        )
    }

    private fun defaultConfiguration(): RedisCacheConfiguration {
        return RedisCacheConfiguration.defaultCacheConfig()
            .disableCachingNullValues()
            .entryTtl(Duration.ofSeconds(5))
            .computePrefixWith(CacheKeyPrefix.simple())
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    StringRedisSerializer()
                )
            )
    }
}
