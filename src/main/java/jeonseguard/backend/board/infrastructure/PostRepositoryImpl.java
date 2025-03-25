package jeonseguard.backend.board.infrastructure;

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
    public Optional<Post> findByCategoryAndId(BoardCategory category, Long id) {
        return jpaRepository.findByCategoryAndId(category, id);
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
