package jeonseguard.backend.comment.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.comment.domain.entity.Comment;

public record CreateCommentResponse(
        @Schema(description = "댓글 ID", example = "10") Long commentId
) {
    public static CreateCommentResponse fromEntity(Comment comment) {
        return new CreateCommentResponse(comment.getId());
    }
}
