package jeonseguard.backend.global.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "openapi.building")
public record BuildingProperties(
        String overviewUri,
        String floorUri,
        String areaUri,
        String serviceKey,
        String categoryCode,
        String listSize
) {
}
