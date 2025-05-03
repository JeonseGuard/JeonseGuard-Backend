package jeonseguard.backend.board.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jeonseguard.backend.board.domain.entity.QHeart;
import jeonseguard.backend.board.domain.repository.HeartQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HeartQueryRepositoryImpl implements HeartQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final QHeart heart = QHeart.heart;

    @Override
    public boolean existsByUserIdAndPostId(Long userId, Long postId) {
        return queryFactory
                .selectOne()
                .from(heart)
                .where(
                        heart.userId.eq(userId),
                        heart.postId.eq(postId)
                )
                .fetchFirst() != null;
    }

    @Override
    public long countByPostId(Long postId) {
        return Optional.ofNullable(queryFactory
                .select(heart.count())
                .from(heart)
                .where(heart.postId.eq(postId))
                .fetchOne()).orElse(0L);
    }
}
