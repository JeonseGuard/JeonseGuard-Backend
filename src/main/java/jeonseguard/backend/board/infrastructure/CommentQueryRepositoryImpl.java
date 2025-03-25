package jeonseguard.backend.board.infrastructure;

import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.domain.repository.CommentQueryRepository;
import jeonseguard.backend.board.presentation.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentQueryRepositoryImpl implements CommentQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final QComment comment = QComment.comment;
    private final QHeart heart = QHeart.heart;

    @Override
    public List<CommentResponse> findAllByPostIdWithHearts(Long userId, Long postId, HeartTarget target) {
        return queryFactory
                .select(new QCommentResponse(
                        comment.id,
                        comment.content,
                        comment.createdBy,
                        createdDate(),
                        heartCount(),
                        heartStatus(userId)
                ))
                .from(comment)
                .where(comment.post.id.eq(postId))
                .orderBy(comment.createdAt.asc())
                .fetch();
    }

    private NumberExpression<Long> heartCount() {
        return Expressions.numberTemplate(
                Long.class,
                "({0})",
                JPAExpressions.select(heart.count())
                        .from(heart)
                        .where(heart.targetId.eq(comment.id)
                                .and(heart.target.eq(HeartTarget.POST)))
        );
    }

    private BooleanExpression heartStatus(Long userId) {
        return JPAExpressions.selectOne()
                .from(heart)
                .where(heart.targetId.eq(comment.id)
                        .and(heart.target.eq(HeartTarget.POST))
                        .and(heart.userId.eq(userId)))
                .exists();
    }

    private DateExpression<LocalDate> createdDate() {
        return Expressions.dateTemplate(
                LocalDate.class,
                "function('date', {0})",
                comment.createdAt
        );
    }
}
