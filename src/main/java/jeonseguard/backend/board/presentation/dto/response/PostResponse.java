package jeonseguard.backend.board.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.board.domain.entity.Post;

import java.time.LocalDate;

public record PostResponse(
        @Schema(description = "게시글 ID") Long postId,
        @Schema(description = "제목") String title,
        @Schema(description = "생성자") String creator,
        @Schema(description = "생성일") LocalDate createdAt,
        @Schema(description = "댓글 수") long commentCount,
        @Schema(description = "좋아요 수") long heartCount
) {
    public static PostResponse of(Post post, long commentCount, long heartCount) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getCreatedBy(),
                post.getCreatedAt().toLocalDate(),
                commentCount,
                heartCount
        );
    }
}
