package jeonseguard.backend.auth.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class LogoutTokenRepository {
    private final RedisTemplate<String, String> redisTemplate;

    private static final long BLACKLIST_EXPIRATION_TIME = 10 * 60 * 60; // 10시간

    public void blacklistToken(String accessToken) {
        redisTemplate.opsForValue().set("blacklist:" + accessToken, "LOGOUT", BLACKLIST_EXPIRATION_TIME, TimeUnit.SECONDS);
    }

    public boolean checkBlacklistedToken(String accessToken) {
        return redisTemplate.hasKey("blacklist:" + accessToken);
    }
}
