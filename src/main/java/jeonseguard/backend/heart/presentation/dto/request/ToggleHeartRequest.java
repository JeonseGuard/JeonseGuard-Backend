package jeonseguard.backend.heart.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record ToggleHeartRequest(
        @Schema(description = "게시글 ID")
        @NotNull(message = "게시글 ID를 입력해 주세요.")
        Long postId
) {
}
