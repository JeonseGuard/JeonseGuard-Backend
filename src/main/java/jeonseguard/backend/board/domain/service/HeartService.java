package jeonseguard.backend.board.domain.service;

import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.domain.factory.HeartFactory;
import jeonseguard.backend.board.domain.repository.HeartRepository;
import jeonseguard.backend.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;

    public boolean checkHeartStatus(Long targetId, HeartTarget target, User user) {
        return heartRepository.existsByTargetIdAndTargetAndUser(targetId, target, user);
    }

    public long countHearts(Long targetId, HeartTarget target) {
        return heartRepository.countByTargetIdAndTarget(targetId, target);
    }

    public void changeHeartStatus(Long targetId, String target, User user) {
        HeartTarget parsedTarget = parseTarget(target);
        boolean exists = heartRepository.existsByTargetIdAndTargetAndUser(targetId, parsedTarget, user);
        applyHeartStatus(targetId, parsedTarget, user, exists);
    }

    public boolean checkPostHeartStatus(Long postId, User user) {
        return heartRepository.existsByTargetIdAndTargetAndUser(postId, HeartTarget.POST, user);
    }

    public boolean checkCommentHeartStatus(Long commentId, User user) {
        return heartRepository.existsByTargetIdAndTargetAndUser(commentId, HeartTarget.COMMENT, user);
    }

    public long countPostHearts(Long targetId) {
        return heartRepository.countByTargetIdAndTarget(targetId, HeartTarget.POST);
    }

    public long countCommentHearts(Long targetId) {
        return heartRepository.countByTargetIdAndTarget(targetId, HeartTarget.COMMENT);
    }

    private void applyHeartStatus(Long targetId, HeartTarget heartTarget, User user, boolean exists) {
        if (exists) {
            heartRepository.deleteByTargetIdAndTargetAndUser(targetId, heartTarget, user);
        } else {
            Heart heart = HeartFactory.createHeart(targetId, heartTarget, user);
            heartRepository.save(heart);
        }
    }

    private HeartTarget parseTarget(String target) {
        return HeartTarget.valueOf(target);
    }
}
