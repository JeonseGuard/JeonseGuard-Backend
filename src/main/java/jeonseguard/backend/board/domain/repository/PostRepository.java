package jeonseguard.backend.board.domain.repository;

import jeonseguard.backend.board.domain.entity.*;

public interface PostRepository {
    Post save(Post post);
    void delete(Post post);
}
