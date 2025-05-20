package jeonseguard.backend.comment.domain.repository;

import jeonseguard.backend.comment.infrastructure.dto.CommentResponse;

import java.util.List;

public interface CommentQueryRepository {
    List<CommentResponse> findAllByPostId(Long postId);
    void deleteAllByPostId(Long postId);
}
