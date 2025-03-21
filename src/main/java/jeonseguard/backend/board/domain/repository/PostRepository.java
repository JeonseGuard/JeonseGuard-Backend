package jeonseguard.backend.board.domain.repository;

import jeonseguard.backend.board.domain.entity.Post;
import org.springframework.data.domain.*;

import java.util.Optional;

public interface PostRepository {
    Optional<Post> findById(Long id);
    Page<Post> findAll(Pageable pageable);
    Post save(Post post);
    void delete(Post post);
}
