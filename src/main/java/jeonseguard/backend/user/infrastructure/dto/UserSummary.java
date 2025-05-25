package jeonseguard.backend.user.infrastructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.user.domain.entity.*;

public record UserSummary(
        @Schema(description = "사용자 ID") Long userId,
        @Schema(description = "닉네임") String nickname,
        @Schema(description = "역할") Role role
) {
    public static UserSummary fromEntity(User user) {
        return new UserSummary(
                user.getId(),
                user.getNickname(),
                user.getRole()
        );
    }
}
