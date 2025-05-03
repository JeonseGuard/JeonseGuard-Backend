package jeonseguard.backend.user.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jeonseguard.backend.global.annotation.AuthenticatedUser;
import jeonseguard.backend.user.application.facade.UserFacade;
import jeonseguard.backend.user.presentation.dto.request.*;
import jeonseguard.backend.user.presentation.dto.respone.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "사용자 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v3/user")
public class UserController {
    private final UserFacade userFacade;

    @Operation(summary = "사용자 개인 정보 조회", description = "JWT 토큰을 기반으로 현재 로그인한 사용자의 개인 정보를 가져옵니다.")
    @GetMapping("/me")
    public ResponseEntity<UserInfoResponse> getUser(@AuthenticatedUser Long userId) {
        return ResponseEntity.ok(userFacade.getUser(userId));
    }

    @Operation(summary = "사용자 닉네임 수정", description = "현재 로그인한 사용자의 닉네임을 수정합니다.")
    @PatchMapping("/nickname")
    public ResponseEntity<Void> updateNickname(@AuthenticatedUser Long userId, @Valid @RequestBody UpdateNicknameRequest request) {
        userFacade.updateNickname(userId, request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "사용자 프로필 이미지 수정", description = "현재 로그인한 사용자의 프로필 이미지를 수정합니다.")
    @PatchMapping("/profile-image")
    public ResponseEntity<Void> updateProfileImage(@AuthenticatedUser Long userId, @Valid @RequestBody UpdateProfileImageRequest request) {
        userFacade.updateProfileImage(userId, request);
        return ResponseEntity.noContent().build();
    }
}
