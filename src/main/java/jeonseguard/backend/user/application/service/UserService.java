package jeonseguard.backend.user.application.service;

import jeonseguard.backend.auth.infrastructure.dto.response.KakaoUserInfoResponse;
import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.user.domain.entity.User;
import jeonseguard.backend.user.domain.factory.UserFactory;
import jeonseguard.backend.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // getUserInfo

    @Transactional
    public User getOrCreateUser(KakaoUserInfoResponse response) {
        return userRepository.findByKakaoId(response.kakaoId())
                .orElseGet(() -> createUser(response));
    }

    @Transactional(readOnly = true)
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    @Transactional
    public User createUser(KakaoUserInfoResponse response) {
        return userRepository.save(UserFactory.fromResponse(response));
    }

    @Transactional
    public void updateNickname(User user, String nickname) {
        user.updateNickname(nickname);
    }

    @Transactional
    public void updateProfileImage(User user, String imageUrl) {
        user.updateProfileImage(imageUrl);
    }
}
