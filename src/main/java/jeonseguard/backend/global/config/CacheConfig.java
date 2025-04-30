package jeonseguard.backend.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.cache.*;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;
import java.util.*;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CacheConfig {
    private final RedisConnectionFactory redisConnectionFactory;

    @Bean
    public CacheManager redisCacheManager() {
        RedisCacheConfiguration defaultConfig = createCacheConfigWithTtl(Duration.ofMinutes(30));
        Map<String, RedisCacheConfiguration> cacheConfigs = new HashMap<>();
        cacheConfigs.put("user", defaultConfig);
        return buildCacheManager(defaultConfig, cacheConfigs);
    }

    private RedisCacheConfiguration createCacheConfigWithTtl(Duration ttl) {
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .disableCachingNullValues()
                .entryTtl(ttl);
    }

    private RedisCacheManager buildCacheManager(RedisCacheConfiguration defaultConfig, Map<String, RedisCacheConfiguration> cacheConfigs) {
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defaultConfig)
                .withInitialCacheConfigurations(cacheConfigs)
                .build();
    }
}
