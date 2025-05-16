package jeonseguard.backend.global.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpHeaders;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .servers(serverList())
                .components(securityComponents())
                .addSecurityItem(securityRequirements());
    }

    private Info apiInfo() {
        return new Info()
                .title("JeonseGuard API Documentation")
                .description("전세 사기 예방 AI 서비스 API")
                .version("1.0.0");
    }

    private Components securityComponents() {
        return new Components()
                .addSecuritySchemes("bearerAuth", jwtSecurityScheme())
                .addSecuritySchemes("kakaoOAuth", kakaoSecurityScheme());
    }

    private SecurityRequirement securityRequirements() {
        return new SecurityRequirement()
                .addList("bearerAuth")
                .addList("kakaoOAuth");
    }

    private SecurityScheme jwtSecurityScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name(HttpHeaders.AUTHORIZATION);
    }

    private SecurityScheme kakaoSecurityScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.OAUTH2)
                .description("카카오 OAuth2 로그인")
                .flows(oauthFlows());
    }

    private OAuthFlows oauthFlows() {
        return new OAuthFlows()
                .authorizationCode(new OAuthFlow()
                        .authorizationUrl("https://kauth.kakao.com/oauth/authorize")
                        .tokenUrl("https://kauth.kakao.com/oauth/token"));
    }

    private List<Server> serverList() {
        return List.of(
                createServer("https://jeonseguard.duckdns.org", "Production Server"),
                createServer("http://localhost:8080", "Local Server")
        );
    }

    private Server createServer(String url, String description) {
        return new Server()
                .url(url)
                .description(description);
    }
}
