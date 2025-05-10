package jeonseguard.backend.global.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "search.naver.news")
public record NaverNewsProperties(
        String clientId,
        String clientSecret
) {
}
