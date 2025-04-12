package jeonseguard.backend.auth.infrastructure.repository;

import jeonseguard.backend.auth.domain.LogoutTokenStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class LogoutTokenRedisRepository implements LogoutTokenStore {
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void blacklistToken(String accessToken, long accessTokenExpirationTime) {
        redisTemplate.opsForValue().set("blacklist:" + accessToken, "LOGOUT", accessTokenExpirationTime, TimeUnit.SECONDS);
    }

    @Override
    public boolean checkBlacklistedToken(String accessToken) {
        return redisTemplate.hasKey("blacklist:" + accessToken);
    }
}
