package jeonseguard.backend.user.presentation.dto.respone;

import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.user.domain.entity.User;

public record UserInfoResponse(
        @Schema(description = "사용자 ID") Long userId,
        @Schema(description = "닉네임") String nickname,
        @Schema(description = "프로필 이미지") String profileImage,
        @Schema(description = "이메일") String email,
        @Schema(description = "역할") String role
) {
    public static UserInfoResponse fromEntity(User user, String role) {
        return new UserInfoResponse(
                user.getId(),
                user.getNickname(),
                user.getProfileImage(),
                user.getEmail(),
                role
        );
    }
}
