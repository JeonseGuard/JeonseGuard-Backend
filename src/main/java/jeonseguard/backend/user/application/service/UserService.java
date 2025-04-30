package jeonseguard.backend.user.application.service;

import jeonseguard.backend.auth.infrastructure.dto.response.KakaoUserInfoResponse;
import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.user.domain.entity.User;
import jeonseguard.backend.user.domain.factory.UserFactory;
import jeonseguard.backend.user.domain.repository.UserRepository;
import jeonseguard.backend.user.presentation.dto.request.*;
import jeonseguard.backend.user.presentation.dto.respone.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User getOrCreateUser(KakaoUserInfoResponse response) {
        return userRepository.findByKakaoId(response.kakaoId())
                .orElseGet(() -> createUser(response));
    }

    @Cacheable(value = "user", key = "'user::id:' + #userId")
    @Transactional(readOnly = true)
    public UserInfoResponse getUserInfo(Long userId) {
        log.debug("Cache MISS → DB 조회: userId={}", userId);
        return userRepository.findById(userId)
                .map(UserInfoResponse::fromEntity)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
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

    @CacheEvict(value = "user", key = "'user::id:' + #user.id")
    @Transactional
    public void updateNickname(User user, UpdateNicknameRequest request) {
        log.info("Cache EVICT → 닉네임 수정: userId={}", user.getId());
        user.updateNickname(request.newNickname());
    }

    @CacheEvict(value = "user", key = "'user::id:' + #user.id")
    @Transactional
    public void updateProfileImage(User user, UpdateProfileImageRequest request) {
        log.info("Cache EVICT → 프로필 이미지 수정: userId={}", user.getId());
        user.updateProfileImage(request.newProfileImage());
    }
}
