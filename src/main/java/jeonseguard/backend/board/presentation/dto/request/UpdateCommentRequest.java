package jeonseguard.backend.board.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdateCommentRequest(
        @Schema(description = "새로운 댓글 내용") String newContent
) {
}
