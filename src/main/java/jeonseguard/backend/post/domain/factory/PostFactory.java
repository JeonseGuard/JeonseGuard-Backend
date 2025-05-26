package jeonseguard.backend.post.domain.factory;

import jeonseguard.backend.post.presentation.dto.request.CreatePostRequest;
import jeonseguard.backend.post.domain.entity.*;
import jeonseguard.backend.user.infrastructure.dto.UserSummary;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostFactory {
    public static Post from(PostCategory category, UserSummary userSummary, CreatePostRequest request) {
        return Post.builder()
                .title(request.title())
                .content(request.content())
                .createdBy(userSummary.nickname())
                .updatedBy(userSummary.nickname())
                .category(category)
                .userId(userSummary.userId())
                .build();
    }
}
