package jeonseguard.backend.board.infrastructure;

import jeonseguard.backend.board.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
}
