package jeonseguard.backend.board.domain.repository;

import jeonseguard.backend.board.domain.entity.Comment;

import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> findById(Long id);
    Comment save(Comment comment);
    void delete(Comment comment);
}
