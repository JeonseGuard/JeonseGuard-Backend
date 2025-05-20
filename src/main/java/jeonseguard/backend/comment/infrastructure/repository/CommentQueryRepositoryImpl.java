package jeonseguard.backend.comment.infrastructure.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jeonseguard.backend.comment.domain.entity.QComment;
import jeonseguard.backend.comment.domain.repository.CommentQueryRepository;
import jeonseguard.backend.comment.infrastructure.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentQueryRepositoryImpl implements CommentQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final QComment comment = QComment.comment;

    @Override
    public List<CommentResponse> findAllByPostId(Long postId) {
        return queryFactory
                .select(new QCommentResponse(
                        comment.id,
                        comment.content,
                        comment.createdBy,
                        comment.createdAt
                ))
                .from(comment)
                .where(comment.postId.eq(postId))
                .orderBy(comment.createdAt.asc())
                .fetch();
    }

    @Override
    public void deleteAllByPostId(Long postId) {
        queryFactory
                .delete(comment)
                .where(comment.postId.eq(postId))
                .execute();
    }
}
