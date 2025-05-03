package jeonseguard.backend.board.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record CommentResponse(
        @Schema(description = "댓글 ID") Long commentId,
        @Schema(description = "내용") String content,
        @Schema(description = "생성자") String creator,
        @Schema(description = "생성일") LocalDateTime createdDateTime
) {
    @QueryProjection
    public CommentResponse {}
}
