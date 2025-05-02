package jeonseguard.backend.board.domain.factory;

import jeonseguard.backend.board.domain.entity.Comment;
import jeonseguard.backend.board.presentation.dto.request.CreateCommentRequest;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentFactory {
    public static Comment fromRequest(Long postId, Long userId, String nickname, CreateCommentRequest request) {
        return Comment.builder()
                .content(request.content())
                .createdBy(nickname)
                .updatedBy(nickname)
                .postId(postId)
                .userId(userId)
                .build();
    }
}
