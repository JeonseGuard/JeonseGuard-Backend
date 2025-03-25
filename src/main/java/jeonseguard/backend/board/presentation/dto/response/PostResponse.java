package jeonseguard.backend.board.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record PostResponse(
        @Schema(description = "게시글 ID") Long postId,
        @Schema(description = "제목") String title,
        @Schema(description = "생성자") String creator,
        @Schema(description = "생성일") LocalDate createdAt,
        @Schema(description = "댓글 수") long commentCount,
        @Schema(description = "좋아요 수") long heartCount
) {
    @QueryProjection // Q 클래스로 직접 조회 가능 → 성능 개선 + 클린 코드
    public PostResponse {}
}
