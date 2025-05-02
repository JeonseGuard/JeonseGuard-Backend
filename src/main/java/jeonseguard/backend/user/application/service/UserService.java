package jeonseguard.backend.user.application.service;

import jeonseguard.backend.auth.infrastructure.dto.response.KakaoUserInfoResponse;
import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.user.domain.entity.User;
import jeonseguard.backend.user.domain.factory.UserFactory;
import jeonseguard.backend.user.domain.repository.UserRepository;
import jeonseguard.backend.user.infrastructure.dto.UserInfoResponse;
import jeonseguard.backend.user.presentation.dto.request.*;
import jeonseguard.backend.user.presentation.dto.respone.UserDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Cacheable(value = "user", key = "'user::id:' + #userId")
    @Transactional(readOnly = true)
    public UserDetailResponse getUserDetail(Long userId) {
        return userRepository.findById(userId)
                .map(UserDetailResponse::fromEntity)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    @Cacheable(value = "user", key = "'user::id:' + #userId")
    @Transactional(readOnly = true)
    public UserInfoResponse getUserInfo(Long userId) {
        return userRepository.findById(userId)
                .map(UserInfoResponse::fromEntity)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

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

    @CacheEvict(value = "user", key = "'user::id:' + #user.id")
    @Transactional
    public void updateNickname(User user, UpdateNicknameRequest request) {
        user.updateNickname(request.newNickname());
    }

    @CacheEvict(value = "user", key = "'user::id:' + #user.id")
    @Transactional
    public void updateProfileImage(User user, UpdateProfileImageRequest request) {
        user.updateProfileImage(request.newProfileImage());
    }
}
