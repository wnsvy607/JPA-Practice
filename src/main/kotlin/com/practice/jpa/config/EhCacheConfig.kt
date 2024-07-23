package com.practice.jpa.config

import org.ehcache.config.builders.CacheConfigurationBuilder
import org.ehcache.config.builders.ExpiryPolicyBuilder
import org.ehcache.config.builders.ResourcePoolsBuilder
import org.ehcache.config.units.MemoryUnit
import org.ehcache.jsr107.Eh107Configuration
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.interceptor.SimpleKey
import org.springframework.cache.jcache.JCacheCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.time.Duration
import javax.cache.Caching

@Configuration
@EnableCaching
class EhCacheConfig {

    @Primary
    @Bean
    fun localCacheManager(): CacheManager {
        return JCacheCacheManager(ehCacheManager())
    }

    private fun ehCacheManager(): javax.cache.CacheManager {
        val provider = Caching.getCachingProvider()
        val cacheManager = provider.cacheManager

        val userCacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(config())
        cacheManager.createCache("User", userCacheConfiguration)

        return cacheManager
    }

    private fun config(): CacheConfigurationBuilder<SimpleKey, MutableList<*>>? {
        return CacheConfigurationBuilder.newCacheConfigurationBuilder(
            SimpleKey::class.java,
            MutableList::class.java,
            ResourcePoolsBuilder
                .heap(100)
                .offheap(10, MemoryUnit.MB)
        )
            .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofDays(1)))
    }
}
