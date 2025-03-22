package jeonseguard.backend.user.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jeonseguard.backend.global.annotation.AuthenticatedUser;
import jeonseguard.backend.user.application.UserFacade;
import jeonseguard.backend.user.presentation.dto.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "사용자 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserFacade userFacade;

    @Operation(summary = "사용자 개인 정보 조회", description = "JWT 토큰을 기반으로 현재 로그인한 사용자의 개인 정보를 가져옵니다.")
    @GetMapping("/me")
    public ResponseEntity<UserInfoResponse> getMyUserInfo(@AuthenticatedUser Long userId) {
        return ResponseEntity.ok(userFacade.getUser(userId));
    }
}
