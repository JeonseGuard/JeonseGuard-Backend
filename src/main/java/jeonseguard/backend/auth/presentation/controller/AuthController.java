package jeonseguard.backend.auth.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jeonseguard.backend.auth.application.AuthFacade;
import jeonseguard.backend.auth.presentation.dto.request.*;
import jeonseguard.backend.auth.presentation.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "인증 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthFacade authFacade;

    @Operation(summary = "로그인", description = "카카오 인가 코드를 이용하여, 엑세스 토큰과 리프레시 토큰을 발급합니다.")
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authFacade.login(request));
    }

    @Operation(summary = "리프레시", description = "리프레시 토큰을 이용하여, 새로운 엑세스 토큰과 리프레시 토큰을 발급합니다.")
    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@Valid @RequestBody RefreshRequest request) {
        return ResponseEntity.ok(authFacade.refresh(request));
    }
}
