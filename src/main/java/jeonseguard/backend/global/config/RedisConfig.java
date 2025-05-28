package jeonseguard.backend.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.*;
import jeonseguard.backend.global.config.properties.RedisProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {
    private final RedisProperties redisProperties;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = createRedisConfiguration();
        LettuceClientConfiguration clientConfig = createLettuceClientConfig();
        return new LettuceConnectionFactory(config, clientConfig);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory, ObjectMapper objectMapper) {
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(objectMapper);
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);
        return template;
    }

    private RedisStandaloneConfiguration createRedisConfiguration() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redisProperties.host(), redisProperties.port());
        config.setPassword(redisProperties.password());
        return config;
    }

    private LettuceClientConfiguration createLettuceClientConfig() {
        return LettuceClientConfiguration.builder()
                .clientOptions(createClientOptions())
                .commandTimeout(Duration.ofMillis(redisProperties.timeout()))
                .build();
    }

    private ClientOptions createClientOptions() {
        return ClientOptions.builder()
                .timeoutOptions(TimeoutOptions.enabled(Duration.ofMillis(redisProperties.timeout())))
                .build();
    }
}
