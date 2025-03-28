package jeonseguard.backend.board.domain.repository;

import jeonseguard.backend.board.domain.entity.HeartTarget;
import jeonseguard.backend.board.presentation.dto.response.CommentResponse;

import java.util.List;

public interface CommentQueryRepository {
    List<CommentResponse> findAllByIdAndPostIdAndTarget(Long userId, Long postId, HeartTarget target);
}
