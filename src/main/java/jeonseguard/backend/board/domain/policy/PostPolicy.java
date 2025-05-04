package jeonseguard.backend.board.domain.policy;

import jeonseguard.backend.board.domain.entity.Post;
import jeonseguard.backend.global.exception.error.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostPolicy {
    public static void validateAuthor(Long userId, Post post, ErrorCode errorCode) {
        if (!post.getUserId().equals(userId)) {
            throw new BusinessException(errorCode);
        }
    }
}
