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

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CacheConfig {
    private final RedisConnectionFactory redisConnectionFactory;

    @Bean
    public CacheManager redisCacheManager(GenericJackson2JsonRedisSerializer serializer) {
        RedisCacheConfiguration defaultRedisCacheConfig = createRedisCacheConfigWithTtl(serializer, 5);
        Map<String, RedisCacheConfiguration> cacheConfigs = new HashMap<>();
        cacheConfigs.put("user", createRedisCacheConfigWithTtl(serializer, 60));
        cacheConfigs.put("board", defaultRedisCacheConfig);
        cacheConfigs.put("post", defaultRedisCacheConfig);
        cacheConfigs.put("comments", defaultRedisCacheConfig);
        cacheConfigs.put("news", createRedisCacheConfigWithTtl(serializer, 1));
        cacheConfigs.put("region", createRedisCacheConfigWithTtl(serializer, 360));
        cacheConfigs.put("buildingRegister", createRedisCacheConfigWithTtl(serializer, 360));
        cacheConfigs.put("transactionJeonseApartment", createRedisCacheConfigWithTtl(serializer, 360));
        cacheConfigs.put("transactionJeonseOfficetel", createRedisCacheConfigWithTtl(serializer, 360));
        cacheConfigs.put("transactionJeonseRowhouse", createRedisCacheConfigWithTtl(serializer, 360));
        cacheConfigs.put("transactionSaleApartment", createRedisCacheConfigWithTtl(serializer, 360));
        cacheConfigs.put("transactionSaleOfficetel", createRedisCacheConfigWithTtl(serializer, 360));
        cacheConfigs.put("transactionSaleRowhouse", createRedisCacheConfigWithTtl(serializer, 360));
        return buildRedisCacheManager(defaultRedisCacheConfig, cacheConfigs);
    }

    private RedisCacheConfiguration createRedisCacheConfigWithTtl(GenericJackson2JsonRedisSerializer serializer, int ttl) {
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(fromSerializer(serializer))
                .disableCachingNullValues()
                .entryTtl(Duration.ofMinutes(ttl));
    }

    private RedisCacheManager buildRedisCacheManager(RedisCacheConfiguration defaultCacheConfig, Map<String, RedisCacheConfiguration> cacheConfigs) {
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defaultCacheConfig)
                .withInitialCacheConfigurations(cacheConfigs)
                .build();
    }
}
