package jeonseguard.backend.board.infrastructure;

import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jeonseguard.backend.board.domain.BoardQueryRepository;
import jeonseguard.backend.board.presentation.dto.BoardResponse;
import jeonseguard.backend.comment.domain.entity.QComment;
import jeonseguard.backend.heart.domain.entity.QHeart;
import jeonseguard.backend.post.domain.entity.*;
import jeonseguard.backend.post.infrastructure.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class BoardQueryRepositoryImpl implements BoardQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final QPost post = QPost.post;
    private final QComment comment = QComment.comment;
    private final QHeart heart = QHeart.heart;
    
    @Override
    public BoardResponse findAllWithCounts(PostCategory category, Pageable pageable) {
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

        Page<PostResponse> page = new PageImpl<>(posts, pageable, total);
        
        return BoardResponse.from(page);
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
                        .where(heart.postId.eq(post.id))
        );
    }
}
