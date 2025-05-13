package jeonseguard.backend.global.config;

import com.fasterxml.jackson.databind.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.cache.*;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;
import java.util.*;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CacheConfig {
    private final RedisConnectionFactory redisConnectionFactory;

    @Bean
    public CacheManager redisCacheManager(ObjectMapper objectMapper) {
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(objectMapper);
        RedisCacheConfiguration defaultRedisCacheConfig = createDefaultRedisCacheConfig(serializer);
        Map<String, RedisCacheConfiguration> cacheConfigs = new HashMap<>();
        cacheConfigs.put("postPage", defaultRedisCacheConfig);
        cacheConfigs.put("postInfo", defaultRedisCacheConfig);
        cacheConfigs.put("postDetail", defaultRedisCacheConfig);
        cacheConfigs.put("commentList", defaultRedisCacheConfig);
        cacheConfigs.put("userInfo", defaultRedisCacheConfig);
        cacheConfigs.put("userDetail", defaultRedisCacheConfig);
        cacheConfigs.put("regionDetail", defaultRedisCacheConfig);
        cacheConfigs.put("buildingRegister", defaultRedisCacheConfig);
        cacheConfigs.put("newsList", defaultRedisCacheConfig.entryTtl(Duration.ofMinutes(1)));
        return buildRedisCacheManager(defaultRedisCacheConfig, cacheConfigs);
    }

    private RedisCacheConfiguration createDefaultRedisCacheConfig(GenericJackson2JsonRedisSerializer serializer) {
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(fromSerializer(serializer))
                .disableCachingNullValues()
                .entryTtl(Duration.ofMinutes(30));
    }

    private RedisCacheManager buildRedisCacheManager(RedisCacheConfiguration defaultCacheConfig, Map<String, RedisCacheConfiguration> cacheConfigs) {
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defaultCacheConfig)
                .withInitialCacheConfigurations(cacheConfigs)
                .build();
    }
}
