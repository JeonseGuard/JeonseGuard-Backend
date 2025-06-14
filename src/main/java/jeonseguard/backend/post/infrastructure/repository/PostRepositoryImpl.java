package jeonseguard.backend.post.infrastructure.repository;

import jeonseguard.backend.post.domain.repository.PostRepository;
import jeonseguard.backend.post.domain.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final PostJpaRepository jpaRepository;

    @Override
    public Optional<Post> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return jpaRepository.save(post);
    }

    @Override
    public void delete(Post post) {
        jpaRepository.delete(post);
    }
}
