package jeonseguard.backend.global.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "building.api")
public record BuildingProperties(
        String overviewUri,
        String floorUri,
        String serviceKey,
        String categoryCode,
        String listSize,
        String pageSize
) {
}
