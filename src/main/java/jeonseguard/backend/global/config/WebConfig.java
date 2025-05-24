package jeonseguard.backend.global.config;

import jeonseguard.backend.auth.infrastructure.resolver.AuthenticatedUserArgumentResolver;
import jeonseguard.backend.global.config.properties.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties({JwtTokenProperties.class, TransactionProperties.class, MaliciousPathProperties.class})
public class WebConfig implements WebMvcConfigurer {
    private final AuthenticatedUserArgumentResolver authenticatedUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authenticatedUserArgumentResolver);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("https://jeonseguard.duckdns.org", "http://localhost:3000", "https://jeonse-guard-frontend.vercel.app")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
