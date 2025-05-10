package jeonseguard.backend.global.config;

import jeonseguard.backend.global.config.properties.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties({JwtTokenProperties.class, KakaoOauthProperties.class, NaverNewsProperties.class, BuildingProperties.class})
public class WebClientConfig {
    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}
