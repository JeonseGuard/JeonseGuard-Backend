package jeonseguard.backend.board.domain.factory;

import jeonseguard.backend.board.domain.entity.Post;
import jeonseguard.backend.board.presentation.dto.request.CreatePostRequest;
import jeonseguard.backend.user.domain.entity.User;

public class PostFactory {
    public static Post fromRequest(CreatePostRequest request, User user) {
        return Post.builder()
                .title(request.title())
                .content(request.content())
                .user(user)
                .createdBy(user.getNickname())
                .updatedBy(user.getNickname())
                .build();
    }
}
