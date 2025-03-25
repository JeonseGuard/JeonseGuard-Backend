package jeonseguard.backend.board.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record PostDetailResponse(
        @Schema(description = "게시글 ID") Long postId,
        @Schema(description = "제목") String title,
        @Schema(description = "내용") String content,
        @Schema(description = "카테고리") String category,
        @Schema(description = "생성자") String creator,
        @Schema(description = "생성일") LocalDate createdDate,
        @Schema(description = "게시글 좋아요 수") long heartCount,
        @Schema(description = "게시글 좋아요 상태") boolean heartStatus
) {
    @QueryProjection
    public PostDetailResponse {}
}
