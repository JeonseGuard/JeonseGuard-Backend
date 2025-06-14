package jeonseguard.backend.comment.domain.policy;

import jeonseguard.backend.comment.domain.entity.Comment;
import jeonseguard.backend.global.exception.error.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentPolicy {
    public static void validateAuthor(Long userId, Comment comment, ErrorCode errorCode) {
        if (!comment.getUserId().equals(userId)) {
            throw new BusinessException(errorCode);
        }
    }
}
