package jeonseguard.backend.board.domain.service;

import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.domain.factory.HeartFactory;
import jeonseguard.backend.board.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;
    private final HeartQueryRepository heartQueryRepository;

    @Transactional(readOnly = true)
    public boolean hasHeart(Long userId, Long targetId, HeartTarget target) {
        return heartQueryRepository.existsByTarget(userId, targetId, target);
    }

    @Transactional(readOnly = true)
    public long countHeart(Long targetId, HeartTarget target) {
        return heartQueryRepository.countByTarget(targetId, target);
    }

    @Transactional
    public void toggleHeart(Long userId, Long targetId, HeartTarget target) {
        if (heartRepository.existsByUserIdAndTargetIdAndTarget(userId, targetId, target)) {
            heartRepository.deleteByUserIdAndTargetIdAndTarget(userId, targetId, target);
        } else {
            Heart heart = HeartFactory.createHeart(userId, targetId, target);
            heartRepository.save(heart);
        }
    }
}
