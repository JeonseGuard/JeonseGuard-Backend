package jeonseguard.backend.comment.infrastructure.repository;

import jeonseguard.backend.comment.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {
}
