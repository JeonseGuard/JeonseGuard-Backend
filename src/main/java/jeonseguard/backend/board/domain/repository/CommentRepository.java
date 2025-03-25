package jeonseguard.backend.board.domain.repository;

import jeonseguard.backend.board.domain.entity.Comment;

import java.util.*;

public interface CommentRepository {
    Optional<Comment> findById(Long id);
    Comment save(Comment comment);
    void delete(Comment comment);
}
