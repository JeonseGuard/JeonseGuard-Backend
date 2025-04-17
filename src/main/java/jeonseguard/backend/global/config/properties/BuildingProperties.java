package jeonseguard.backend.global.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "building.api")
public record BuildingProperties(
        String serviceKey,
        String listSize,
        String pageSize
) {
}
