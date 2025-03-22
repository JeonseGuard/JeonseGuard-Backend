package jeonseguard.backend.board.infrastructure;

import jeonseguard.backend.board.domain.entity.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByCategory(BoardCategory category, Pageable pageable);
    Optional<Post> findByCategoryAndId(BoardCategory category, Long id);
}
