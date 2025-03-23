package jeonseguard.backend.board.infrastructure;

import jeonseguard.backend.board.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {
    long countByPostId(Long postId);
    List<Comment> findByPostId(Long postId);
}
