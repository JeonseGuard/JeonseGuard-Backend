package jeonseguard.backend.post.domain.repository;

import jeonseguard.backend.post.domain.entity.Post;

import java.util.Optional;

public interface PostRepository {
    Optional<Post> findById(Long id);
    Post save(Post post);
    void delete(Post post);
}
