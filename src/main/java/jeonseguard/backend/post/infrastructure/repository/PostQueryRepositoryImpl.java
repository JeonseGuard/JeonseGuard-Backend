package jeonseguard.backend.post.infrastructure.repository;

import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jeonseguard.backend.heart.domain.entity.QHeart;
import jeonseguard.backend.post.domain.entity.QPost;
import jeonseguard.backend.post.domain.repository.PostQueryRepository;
import jeonseguard.backend.post.infrastructure.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class PostQueryRepositoryImpl implements PostQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final QPost post = QPost.post;
    private final QHeart heart = QHeart.heart;

    @Override
    public Optional<PostDetailResponse> findDetailByUserIdAndId(Long userId, Long postId) {
        return Optional.ofNullable(queryFactory
                .select(new QPostDetailResponse(
                        post.id,
                        post.title,
                        post.content,
                        post.category.stringValue(),
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

    private NumberExpression<Long> heartCount() {
        return Expressions.numberTemplate(
                Long.class,
                "({0})",
                JPAExpressions.select(heart.count().longValue())
                        .from(heart)
                        .where(heart.postId.eq(post.id))
        );
    }

    private BooleanExpression heartStatus(Long userId) {
        return JPAExpressions.selectOne()
                .from(heart)
                .where(heart.postId.eq(post.id)
                        .and(heart.userId.eq(userId))
                )
                .exists();
    }
}
