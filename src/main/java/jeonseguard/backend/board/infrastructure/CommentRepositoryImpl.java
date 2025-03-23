package jeonseguard.backend.board.infrastructure;

import jeonseguard.backend.board.domain.entity.Comment;
import jeonseguard.backend.board.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
    private final CommentJpaRepository jpaRepository;

    @Override
    public Optional<Comment> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return jpaRepository.save(comment);
    }

    @Override
    public void delete(Comment comment) {
        jpaRepository.delete(comment);
    }

    @Override
    public List<Comment> findAllByPostId(Long postId) {
        return jpaRepository.findByPostId(postId);
    }

    @Override
    public long countByPostId(Long postId) {
        return jpaRepository.countByPostId(postId);
    }
}
