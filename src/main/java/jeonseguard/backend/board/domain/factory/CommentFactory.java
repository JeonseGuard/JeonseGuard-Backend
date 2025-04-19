package jeonseguard.backend.board.domain.factory;

import jeonseguard.backend.board.domain.entity.Comment;
import jeonseguard.backend.board.presentation.dto.request.CreateCommentRequest;
import jeonseguard.backend.user.domain.entity.User;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentFactory {
    public static Comment fromRequest(Long postId, User user, CreateCommentRequest request) {
        return Comment.builder()
                .content(request.content())
                .createdBy(user.getNickname())
                .updatedBy(user.getNickname())
                .postId(postId)
                .user(user)
                .build();
    }
}
