package jeonseguard.backend.board.infrastructure;

import jeonseguard.backend.board.domain.entity.Post;
import jeonseguard.backend.board.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
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
    public Page<Post> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable);
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
