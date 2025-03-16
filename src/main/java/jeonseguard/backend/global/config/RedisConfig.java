package jeonseguard.backend.global.config;

import io.lettuce.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Value("${spring.data.redis.password}")
    private String redisPassword;

    @Value("${spring.data.redis.timeout}")
    private long redisTimeout;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = createRedisConfiguration();
        LettuceClientConfiguration clientConfig = createLettuceClientConfig();
        return new LettuceConnectionFactory(config, clientConfig);
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        return template;
    }

    private RedisStandaloneConfiguration createRedisConfiguration() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redisHost, redisPort);
        config.setPassword(redisPassword);
        return config;
    }

    private LettuceClientConfiguration createLettuceClientConfig() {
        return LettuceClientConfiguration.builder()
                .clientOptions(createClientOptions())
                .commandTimeout(Duration.ofMillis(redisTimeout))
                .build();
    }

    private ClientOptions createClientOptions() {
        return ClientOptions.builder()
                .timeoutOptions(TimeoutOptions.enabled(Duration.ofMillis(redisTimeout)))
                .build();
    }
}
