package jeonseguard.backend.board.domain.factory;

import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.presentation.dto.request.CreateCommentRequest;
import jeonseguard.backend.user.domain.entity.User;

public class CommentFactory {
    public static Comment fromRequest(User user, Post post, CreateCommentRequest request) {
        return Comment.builder()
                .content(request.content())
                .createdBy(user.getNickname())
                .updatedBy(user.getNickname())
                .post(post)
                .user(user)
                .build();
    }
}
