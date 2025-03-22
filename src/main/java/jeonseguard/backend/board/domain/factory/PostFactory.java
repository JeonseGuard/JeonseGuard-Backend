package jeonseguard.backend.board.domain.factory;

import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.presentation.dto.request.CreatePostRequest;
import jeonseguard.backend.user.domain.entity.User;

public class PostFactory {
    public static Post fromRequest(BoardCategory category, User user, CreatePostRequest request) {
        return Post.builder()
                .title(request.title())
                .content(request.content())
                .user(user)
                .category(category)
                .createdBy(user.getNickname())
                .updatedBy(user.getNickname())
                .build();
    }
}
