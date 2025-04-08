package jeonseguard.backend.global.config;

import jeonseguard.backend.global.config.properties.RegionCodeProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RegionCodeProperties.class)
public class RegionCodeConfig {
}
