package jeonseguard.backend.board.domain.repository;

import jeonseguard.backend.board.domain.entity.*;
import org.springframework.data.domain.*;

import java.util.Optional;

public interface PostRepository {
    Page<Post> findAllByCategory(BoardCategory category, Pageable pageable);
    Optional<Post> findByCategoryAndId(BoardCategory category, Long id);
    Post save(Post post);
    void delete(Post post);
}
