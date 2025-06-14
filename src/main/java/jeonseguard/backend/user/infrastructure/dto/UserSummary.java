package jeonseguard.backend.user.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.user.domain.entity.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public record UserSummary(
        @Schema(description = "사용자 ID") Long userId,
        @Schema(description = "닉네임") String nickname,
        @Schema(description = "역할") Role role
) {
    public static UserSummary from(User user) {
        return new UserSummary(
                user.getId(),
                user.getNickname(),
                user.getRole()
        );
    }
}
