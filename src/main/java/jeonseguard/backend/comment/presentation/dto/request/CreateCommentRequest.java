package jeonseguard.backend.comment.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record CreateCommentRequest(
        @Schema(description = "게시글 ID")
        @NotNull(message = "게시글 ID를 입력해 주세요.")
        Long postId,

        @Schema(description = "댓글 내용")
        @NotBlank(message = "댓글 내용을 입력해 주세요.")
        String content
) {
}
