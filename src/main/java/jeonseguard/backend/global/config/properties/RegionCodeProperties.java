package jeonseguard.backend.global.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "region-code.init")
public record RegionCodeProperties(
        String finalCode
) {
}
