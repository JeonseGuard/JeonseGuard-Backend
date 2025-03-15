package jeonseguard.backend.auth.presentation.controller;

import jeonseguard.backend.auth.application.AuthFacade;
import jeonseguard.backend.auth.presentation.dto.request.TokenRequest;
import jeonseguard.backend.auth.presentation.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthFacade authFacade;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody TokenRequest request) {
        return ResponseEntity.ok(authFacade.login(request));
    }

    @GetMapping("/callback/kakao")
    public String kakaoCallback(@RequestParam("code") String code) {
        return "redirect:/login-success?code=" + code;
    }
}
