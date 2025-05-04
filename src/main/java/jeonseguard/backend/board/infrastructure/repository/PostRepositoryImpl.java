package jeonseguard.backend.board.infrastructure.repository;

import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.domain.repository.PostRepository;
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
