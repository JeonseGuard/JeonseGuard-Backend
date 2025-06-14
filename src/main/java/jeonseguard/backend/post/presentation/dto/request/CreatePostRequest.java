package jeonseguard.backend.post.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record CreatePostRequest(
        @Schema(description = "게시글 제목")
        @NotBlank(message = "게시글 제목을 입력해 주세요.")
        @Size(min = 1, max = 50, message = "게시글 제목은 1자 이상 50자 이하로 입력해 주세요.")
        String title,

        @Schema(description = "게시글 내용")
        @NotBlank(message = "게시글 내용을 입력해 주세요.")
        String content
) {
}
