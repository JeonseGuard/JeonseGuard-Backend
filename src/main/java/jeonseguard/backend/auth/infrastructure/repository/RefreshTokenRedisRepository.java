package jeonseguard.backend.auth.infrastructure.repository;

import jeonseguard.backend.auth.domain.RefreshTokenStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRedisRepository implements RefreshTokenStore {
    private final RedisTemplate<String, String> redisTemplate;

    private static final String KEY_PREFIX = "refresh:";

    @Override
    public void saveRefreshToken(Long userId, String refreshToken, long refreshExpirationTime) {
        redisTemplate.opsForValue().set(KEY_PREFIX + userId, refreshToken, refreshExpirationTime, TimeUnit.SECONDS);
    }

    @Override
    public String getRefreshToken(Long userId) {
        return redisTemplate.opsForValue().get(KEY_PREFIX + userId);
    }

    @Override
    public void removeRefreshToken(Long userId) {
        redisTemplate.delete(KEY_PREFIX + userId);
    }
}
