package jeonseguard.backend.global.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "openapi.building.register")
public record BuildingRegisterProperties(
        String overviewUri,
        String floorUri,
        String areaUri,
        String serviceKey,
        String categoryCode,
        String listSize
) {
}
