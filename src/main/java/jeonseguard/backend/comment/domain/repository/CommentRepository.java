package jeonseguard.backend.comment.domain.repository;

import jeonseguard.backend.comment.domain.entity.Comment;

import java.util.*;

public interface CommentRepository {
    Optional<Comment> findById(Long id);
    Comment save(Comment comment);
    void delete(Comment comment);
}
