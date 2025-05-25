package jeonseguard.backend.comment.domain.factory;

import jeonseguard.backend.comment.domain.entity.Comment;
import jeonseguard.backend.comment.presentation.dto.request.CreateCommentRequest;
import jeonseguard.backend.user.infrastructure.dto.UserSummary;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentFactory {
    public static Comment from(UserSummary userSummary, CreateCommentRequest request) {
        return Comment.builder()
                .content(request.content())
                .createdBy(userSummary.nickname())
                .updatedBy(userSummary.nickname())
                .postId(request.postId())
                .userId(userSummary.userId())
                .build();
    }
}
