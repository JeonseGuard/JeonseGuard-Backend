package jeonseguard.backend.user.application.service;

import jeonseguard.backend.auth.infrastructure.dto.KakaoUserInfoResponse;
import jeonseguard.backend.user.domain.entity.User;
import jeonseguard.backend.user.domain.factory.UserFactory;
import jeonseguard.backend.user.domain.repository.UserRepository;
import jeonseguard.backend.user.presentation.dto.request.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static jeonseguard.backend.global.constant.CacheKey.USER_ID_PREFIX;

@Transactional
@Service
@RequiredArgsConstructor
public class UserCommandService {
    private final UserRepository userRepository;

    public User createUser(KakaoUserInfoResponse response) {
        User user = UserFactory.from(response);
        return userRepository.save(user);
    }

    @CacheEvict(value = "user", key = "'" + USER_ID_PREFIX + "' + #user.id")
    public void updateNickname(User user, UpdateNicknameRequest request) {
        user.updateNickname(request.newNickname());
    }

    @CacheEvict(value = "user", key = "'" + USER_ID_PREFIX + "' + #user.id")
    public void updateProfileImage(User user, UpdateProfileImageRequest request) {
        user.updateProfileImage(request.newProfileImage());
    }
}
