package jeonseguard.backend.board.domain.repository;

import jeonseguard.backend.board.infrastructure.dto.CommentResponse;

import java.util.List;

public interface CommentQueryRepository {
    List<CommentResponse> findAllByIdAndPostId(Long userId, Long postId);
}
