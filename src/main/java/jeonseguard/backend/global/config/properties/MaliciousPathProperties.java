package jeonseguard.backend.global.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "malicious")
public record MaliciousPathProperties(
        List<String> blockedPathPrefixes
) {
}
