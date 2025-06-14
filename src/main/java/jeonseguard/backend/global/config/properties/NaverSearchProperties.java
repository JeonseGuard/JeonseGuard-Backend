package jeonseguard.backend.global.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "search.naver")
public record NaverSearchProperties(
        String clientId,
        String clientSecret,
        String newsEndpoint,
        String newsQuery,
        String newsSort,
        String newsDisplay
) {
}
