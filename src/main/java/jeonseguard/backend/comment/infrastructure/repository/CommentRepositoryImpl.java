package jeonseguard.backend.comment.infrastructure.repository;

import jeonseguard.backend.comment.domain.entity.Comment;
import jeonseguard.backend.comment.domain.repository.CommentRepository;
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
}
