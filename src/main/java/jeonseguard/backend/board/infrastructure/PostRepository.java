package jeonseguard.backend.board.infrastructure;

import jeonseguard.backend.board.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Post, Long> {
}
