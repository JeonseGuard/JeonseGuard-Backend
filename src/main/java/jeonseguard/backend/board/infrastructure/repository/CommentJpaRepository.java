package jeonseguard.backend.board.infrastructure.repository;

import jeonseguard.backend.board.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {
}
