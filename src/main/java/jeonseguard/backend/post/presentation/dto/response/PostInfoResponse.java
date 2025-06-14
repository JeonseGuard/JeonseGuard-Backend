package jeonseguard.backend.post.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.comment.infrastructure.dto.CommentResponse;
import jeonseguard.backend.post.infrastructure.dto.PostSummary;

import java.time.LocalDateTime;
import java.util.List;

public record PostInfoResponse(
        @Schema(description = "게시글 ID") Long postId,
        @Schema(description = "제목") String title,
        @Schema(description = "내용") String content,
        @Schema(description = "카테고리") String category,
        @Schema(description = "생성자") String creator,
        @Schema(description = "생성일") LocalDateTime createdDateTime,
        @Schema(description = "게시글 좋아요 수") Long heartCount,
        @Schema(description = "게시글 좋아요 상태") Boolean heartStatus,
        @Schema(description = "댓글 목록") List<CommentResponse> comments
) {
    public static PostInfoResponse of(PostSummary postSummary, List<CommentResponse> comments) {
        return new PostInfoResponse(
                postSummary.postId(),
                postSummary.title(),
                postSummary.content(),
                postSummary.category(),
                postSummary.creator(),
                postSummary.createdDateTime(),
                postSummary.heartCount(),
                postSummary.heartStatus(),
                comments
        );
    }
}
