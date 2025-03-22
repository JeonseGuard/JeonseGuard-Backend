package jeonseguard.backend.board.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.board.domain.entity.Post;

public record CreatePostResponse(
        @Schema(description = "게시글 ID", example = "100") Long postId
) {
    public static CreatePostResponse fromEntity(Post post) {
        return new CreatePostResponse(post.getId());
    }
}
