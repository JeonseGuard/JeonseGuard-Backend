package jeonseguard.backend.board.domain.factory;

import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.presentation.dto.request.CreatePostRequest;
import jeonseguard.backend.user.infrastructure.dto.UserInfoResponse;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostFactory {
    public static Post fromRequest(BoardCategory category, UserInfoResponse response, CreatePostRequest request) {
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
