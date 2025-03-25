package jeonseguard.backend.board.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.domain.repository.HeartQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class HeartQueryRepositoryImpl implements HeartQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final QHeart heart = QHeart.heart;

    @Override
    public boolean existsByTarget(Long userId, Long targetId, HeartTarget target) {
        return queryFactory
                .selectOne()
                .from(heart)
                .where(
                        heart.userId.eq(userId),
                        heart.targetId.eq(targetId),
                        heart.target.eq(target)
                )
                .fetchFirst() != null;
    }

    @Override
    public long countByTarget(Long targetId, HeartTarget target) {
        return Optional.ofNullable(queryFactory
                .select(heart.count())
                .from(heart)
                .where(
                        heart.targetId.eq(targetId),
                        heart.target.eq(target)
                )
                .fetchOne()).orElse(0L);
    }

    @Override
    public Set<Long> findHeartedTargetIds(Long userId, List<Long> targetIds, HeartTarget target) {
        return new HashSet<>(queryFactory
                .select(heart.targetId)
                .from(heart)
                .where(
                        heart.userId.eq(userId),
                        heart.targetId.in(targetIds),
                        heart.target.eq(target)
                )
                .fetch()
        );
    }
}
