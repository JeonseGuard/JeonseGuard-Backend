package jeonseguard.backend.board.infrastructure;

import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.domain.repository.PostQueryRepository;
import jeonseguard.backend.board.presentation.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class PostQueryRepositoryImpl implements PostQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final QPost post = QPost.post;
    private final QComment comment = QComment.comment;
    private final QHeart heart = QHeart.heart;

    @Override
    public Page<PostResponse> findAllWithCounts(BoardCategory category, Pageable pageable) {
        List<PostResponse> posts = queryFactory
                .select(new QPostResponse(
                        post.id,
                        post.title,
                        post.createdBy,
                        post.createdAt,
                        commentCount(),
                        heartCount()
                ))
                .from(post)
                .where(post.category.eq(category))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(post.createdAt.desc())
                .fetch();

        long total = Optional.ofNullable(queryFactory
                .select(post.count())
                .from(post)
                .where(post.category.eq(category))
                .fetchOne()).orElse(0L);

        return new PageImpl<>(posts, pageable, total);
    }

    @Override
    public Optional<PostDetailResponse> findDetailByUserIdAndIdAndCategory(Long userId, Long postId, String category) {
        return Optional.ofNullable(queryFactory
                .select(new QPostDetailResponse(
                        post.id,
                        post.title,
                        post.content,
                        Expressions.constant(category),
                        post.createdBy,
                        post.createdAt,
                        heartCount(),
                        heartStatus(userId)
                ))
                .from(post)
                .where(post.id.eq(postId))
                .fetchOne()
        );
    }

    @Override
    public Optional<Post> findByUserIdAndIdAndCategory(Long userId, Long postId, BoardCategory category) {
        return Optional.ofNullable(queryFactory
                .selectFrom(post)
                .where(
                        post.id.eq(postId),
                        post.user.id.eq(userId),
                        post.category.eq(category)
                )
                .fetchOne()
        );
    }

    private NumberExpression<Long> commentCount() {
        return Expressions.numberTemplate(
                Long.class,
                "({0})",
                JPAExpressions.select(comment.count().longValue())
                        .from(comment)
                        .where(comment.postId.eq(post.id))
        );
    }

    private NumberExpression<Long> heartCount() {
        return Expressions.numberTemplate(
                Long.class,
                "({0})",
                JPAExpressions.select(heart.count().longValue())
                        .from(heart)
                        .where(heart.targetId.eq(post.id)
                                .and(heart.target.eq(HeartTarget.POST)))
        );
    }

    private BooleanExpression heartStatus(Long userId) {
        return JPAExpressions.selectOne()
                .from(heart)
                .where(heart.targetId.eq(post.id)
                        .and(heart.target.eq(HeartTarget.POST))
                        .and(heart.userId.eq(userId)))
                .exists();
    }
}
