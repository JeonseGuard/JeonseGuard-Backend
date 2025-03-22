package jeonseguard.backend.board.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.board.domain.entity.Post;

import java.time.LocalDate;
import java.util.List;

public record PostInfoResponse(
        @Schema(description = "게시글 ID") Long postId,
        @Schema(description = "제목") String title,
        @Schema(description = "내용") String content,
        @Schema(description = "카테고리") String category,
        @Schema(description = "생성자") String creator,
        @Schema(description = "생성일") LocalDate createdAt,
        @Schema(description = "댓글 목록") List<CommentResponse> comments
) {
    public static PostInfoResponse fromEntity(Post post) {
        return new PostInfoResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCategory().toString(),
                post.getCreatedBy(),
                post.getCreatedAt().toLocalDate(),
                toCommentResponses(post)
        );
    }

    private static List<CommentResponse> toCommentResponses(Post post) {
        return post.getComments().stream()
                .map(CommentResponse::fromEntity)
                .toList();
    }
}
