package jeonseguard.backend.board.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.board.domain.entity.Post;

import java.time.LocalDate;

public record PostInfoResponse(
        @Schema(description = "게시글 ID") Long postId,
        @Schema(description = "제목") String title,
        @Schema(description = "내용") String content,
        @Schema(description = "카테고리") String category,
        @Schema(description = "생성자") String creator,
        @Schema(description = "생성일") LocalDate createdAt
) {
    public static PostInfoResponse fromEntity(Post post) {
        return new PostInfoResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCategory().toString(),
                post.getCreatedBy(),
                post.getCreatedAt().toLocalDate()
        );
    }
}
