package jeonseguard.backend.board.domain.factory;

import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.presentation.dto.request.CreatePostRequest;
import jeonseguard.backend.user.domain.entity.User;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostFactory {
    public static Post fromRequest(User user, BoardCategory category, CreatePostRequest request) {
        return Post.builder()
                .title(request.title())
                .content(request.content())
                .createdBy(user.getNickname())
                .updatedBy(user.getNickname())
                .category(category)
                .user(user)
                .build();
    }
}
