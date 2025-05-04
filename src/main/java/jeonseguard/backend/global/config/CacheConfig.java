package jeonseguard.backend.global.config;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
    public CacheManager redisCacheManager() {
        ObjectMapper objectMapper = createObjectMapper();
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(objectMapper);
        RedisCacheConfiguration defaultRedisCacheConfig = createDefaultRedisCacheConfig(serializer);
        Map<String, RedisCacheConfiguration> cacheConfigs = new HashMap<>();
        cacheConfigs.put("postPage", defaultRedisCacheConfig);
        cacheConfigs.put("postDetail", defaultRedisCacheConfig);
        cacheConfigs.put("commentList", defaultRedisCacheConfig);
        cacheConfigs.put("regionDetail", defaultRedisCacheConfig);
        cacheConfigs.put("userInfo", defaultRedisCacheConfig);
        cacheConfigs.put("userDetail", defaultRedisCacheConfig);
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

    private ObjectMapper createObjectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
                .activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}
