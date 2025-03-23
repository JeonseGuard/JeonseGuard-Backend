package jeonseguard.backend.board.domain.service;

import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.domain.factory.HeartFactory;
import jeonseguard.backend.board.domain.repository.HeartRepository;
import jeonseguard.backend.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;

    @Transactional
    public long countHearts(Long targetId, HeartTarget target) {
        return heartRepository.countByTargetIdAndTarget(targetId, target);
    }

    @Transactional
    public boolean checkHeartStatus(Long targetId, HeartTarget target, User user) {
        return heartRepository.existsByTargetIdAndTargetAndUser(targetId, target, user);
    }

    @Transactional
    public void changeHeart(boolean heartStatus, Long targetId, HeartTarget target, User user) {
        if (heartStatus) {
            heartRepository.deleteByTargetIdAndTargetAndUser(targetId, target, user);
        } else {
            Heart heart = HeartFactory.createHeart(targetId, target, user);
            heartRepository.save(heart);
        }
    }
}
