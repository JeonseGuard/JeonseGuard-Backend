package jeonseguard.backend.user.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record UpdateProfileImageRequest(
        @Schema(description = "새로운 프로필 이미지")
        @NotBlank(message = "새로운 프로필 이미지를 입력해 주세요.")
        String newProfileImage
) {
}
