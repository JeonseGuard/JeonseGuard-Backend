package jeonseguard.backend.comment.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record UpdateCommentRequest(
        @Schema(description = "새로운 댓글 내용")
        @NotBlank(message = "새로운 댓글 내용을 입력해 주세요.")
        String newContent
) {
}
