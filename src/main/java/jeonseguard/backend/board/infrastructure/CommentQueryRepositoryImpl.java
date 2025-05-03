package jeonseguard.backend.board.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jeonseguard.backend.board.domain.entity.QComment;
import jeonseguard.backend.board.domain.repository.CommentQueryRepository;
import jeonseguard.backend.board.presentation.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentQueryRepositoryImpl implements CommentQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final QComment comment = QComment.comment;

    @Override
    public List<CommentResponse> findAllByIdAndPostId(Long userId, Long postId) {
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
}
