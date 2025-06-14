package jeonseguard.backend.auth.infrastructure.repository;

import jeonseguard.backend.auth.domain.RefreshTokenStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

import static jeonseguard.backend.global.constant.CacheKey.AUTH_REFRESH_ID_PREFIX;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRedisRepository implements RefreshTokenStore {
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void saveRefreshToken(Long userId, String refreshToken, long refreshExpirationTime) {
        redisTemplate.opsForValue().set(AUTH_REFRESH_ID_PREFIX + userId, refreshToken, refreshExpirationTime, TimeUnit.SECONDS);
    }

    @Override
    public String getRefreshToken(Long userId) {
        return redisTemplate.opsForValue().get(AUTH_REFRESH_ID_PREFIX + userId);
    }

    @Override
    public void removeRefreshToken(Long userId) {
        redisTemplate.delete(AUTH_REFRESH_ID_PREFIX + userId);
    }
}
