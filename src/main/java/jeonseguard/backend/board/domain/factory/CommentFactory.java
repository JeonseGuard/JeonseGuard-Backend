package jeonseguard.backend.board.domain.factory;

import jeonseguard.backend.board.domain.entity.Comment;
import jeonseguard.backend.board.presentation.dto.request.CreateCommentRequest;
import jeonseguard.backend.user.infrastructure.dto.UserInfoResponse;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentFactory {
    public static Comment fromRequest(Long postId, UserInfoResponse response, CreateCommentRequest request) {
        return Comment.builder()
                .content(request.content())
                .createdBy(response.nickname())
                .updatedBy(response.nickname())
                .postId(postId)
                .userId(response.userId())
                .build();
    }
}
