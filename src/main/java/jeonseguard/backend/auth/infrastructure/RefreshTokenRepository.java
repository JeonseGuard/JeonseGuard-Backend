package jeonseguard.backend.auth.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepository {
    private final RedisTemplate<String, String> redisTemplate;

    private static final long REFRESH_EXPIRATION_TIME = 5 * 24 * 60 * 60; // 5일

    public void saveRefreshToken(Long userId, String refreshToken) {
        redisTemplate.opsForValue().set("refresh:" + userId, refreshToken, REFRESH_EXPIRATION_TIME, TimeUnit.SECONDS);
    }

    public String getRefreshToken(Long userId) {
        return redisTemplate.opsForValue().get("refresh:" + userId);
    }

    public void removeRefreshToken(Long userId) {
        redisTemplate.delete("refresh:" + userId);
    }
}
