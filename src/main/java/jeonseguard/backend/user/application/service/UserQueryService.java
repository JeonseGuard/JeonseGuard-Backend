package jeonseguard.backend.user.application.service;

import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.user.domain.entity.User;
import jeonseguard.backend.user.domain.repository.UserRepository;
import jeonseguard.backend.user.infrastructure.dto.UserSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static jeonseguard.backend.global.constant.CacheKey.USER_ID_PREFIX;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class UserQueryService {
    private final UserRepository userRepository;

    @Cacheable(value = "user", key = "'" + USER_ID_PREFIX + "' + #userId")
    public UserSummary getUserSummary(Long userId) {
        return userRepository.findById(userId)
                .map(UserSummary::from)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_SUMMARY_NOT_FOUND));
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    public Optional<User> getUserByKakaoId(Long kakaoId) {
        return userRepository.findByKakaoId(kakaoId);
    }
}
