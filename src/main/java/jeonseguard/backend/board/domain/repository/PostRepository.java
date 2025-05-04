package jeonseguard.backend.board.domain.repository;

import jeonseguard.backend.board.domain.entity.*;

import java.util.Optional;

public interface PostRepository {
    Optional<Post> findById(Long id);
    Post save(Post post);
    void delete(Post post);
}
