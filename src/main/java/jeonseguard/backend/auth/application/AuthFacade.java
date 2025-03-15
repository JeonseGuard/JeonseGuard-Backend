package jeonseguard.backend.auth.application;

import jeonseguard.backend.auth.domain.AuthService;
import jeonseguard.backend.auth.presentation.dto.request.TokenRequest;
import jeonseguard.backend.auth.presentation.dto.response.*;
import jeonseguard.backend.user.domain.entity.User;
import jeonseguard.backend.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFacade {
    private final AuthService authService;
    private final UserService userService;

    public TokenResponse login(TokenRequest request) {
        KakaoUserInfoResponse kakaoUserInfo = authService.getKakaoUserInfo(request);
        User user = userService.getOrCreateUser(kakaoUserInfo);
        return authService.getTokens(user.getId());
    }
}
