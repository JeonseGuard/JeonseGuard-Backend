package jeonseguard.backend.comment.domain.factory;

import jeonseguard.backend.comment.domain.entity.Comment;
import jeonseguard.backend.comment.presentation.dto.request.CreateCommentRequest;
import jeonseguard.backend.user.infrastructure.dto.UserDetailResponse;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentFactory {
    public static Comment from(UserDetailResponse response, CreateCommentRequest request) {
        return Comment.builder()
                .content(request.content())
                .createdBy(response.nickname())
                .updatedBy(response.nickname())
                .postId(request.postId())
                .userId(response.userId())
                .build();
    }
}
