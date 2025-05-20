package jeonseguard.backend.post.infrastructure.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record PostResponse(
        @Schema(description = "게시글 ID") Long postId,
        @Schema(description = "제목") String title,
        @Schema(description = "생성자") String creator,
        @Schema(description = "생성일") LocalDateTime createdDateTime,
        @Schema(description = "댓글 수") Long commentCount,
        @Schema(description = "좋아요 수") Long heartCount
) {
    @QueryProjection
    public PostResponse {}
}
