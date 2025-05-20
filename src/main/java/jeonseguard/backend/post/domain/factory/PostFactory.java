package jeonseguard.backend.post.domain.factory;

import jeonseguard.backend.post.presentation.dto.request.CreatePostRequest;
import jeonseguard.backend.post.domain.entity.*;
import jeonseguard.backend.user.infrastructure.dto.UserDetailResponse;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostFactory {
    public static Post from(PostCategory category, UserDetailResponse response, CreatePostRequest request) {
        return Post.builder()
                .title(request.title())
                .content(request.content())
                .createdBy(response.nickname())
                .updatedBy(response.nickname())
                .category(category)
                .userId(response.userId())
                .build();
    }
}
