package jeonseguard.backend.global.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpHeaders;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .components(securityComponents())
                .addServersItem(new Server().url("/"))
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
                .addSecuritySchemes("bearerAuth", jwtSecurityScheme());
    }

    private SecurityRequirement securityRequirements() {
        return new SecurityRequirement()
                .addList("bearerAuth");
    }

    private SecurityScheme jwtSecurityScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name(HttpHeaders.AUTHORIZATION);
    }
}
