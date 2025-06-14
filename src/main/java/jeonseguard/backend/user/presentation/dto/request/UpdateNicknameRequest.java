package jeonseguard.backend.user.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record UpdateNicknameRequest(
        @Schema(description = "새로운 닉네임")
        @NotBlank(message = "새로운 닉네임을 입력해 주세요.")
        String newNickname
) {
}
