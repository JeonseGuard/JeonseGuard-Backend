package jeonseguard.backend.board.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.board.domain.entity.Comment;

import java.time.LocalDate;

public record CommentResponse(
        @Schema(description = "댓글 ID") Long commentId,
        @Schema(description = "내용") String content,
        @Schema(description = "생성자") String creator,
        @Schema(description = "생성일") LocalDate createdAt,
        @Schema(description = "댓글 좋아요 수") long heartCount,
        @Schema(description = "댓글 좋아요 상태") boolean heartStatus
) {
    public static CommentResponse of(Comment comment, HeartResponse heart) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedBy(),
                comment.getCreatedAt().toLocalDate(),
                heart.count(),
                heart.status()
        );
    }
}
