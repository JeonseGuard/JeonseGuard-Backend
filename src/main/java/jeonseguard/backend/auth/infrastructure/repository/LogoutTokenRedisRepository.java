package jeonseguard.backend.auth.infrastructure.repository;

import jeonseguard.backend.auth.domain.LogoutTokenStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

import static jeonseguard.backend.global.constant.CacheKey.AUTH_LOGOUT_TOKEN_PREFIX;

@Repository
@RequiredArgsConstructor
public class LogoutTokenRedisRepository implements LogoutTokenStore {
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void blacklistToken(String accessToken, long accessTokenExpirationTime) {
        redisTemplate.opsForValue().set(AUTH_LOGOUT_TOKEN_PREFIX + accessToken, "LOGOUT", accessTokenExpirationTime, TimeUnit.SECONDS);
    }

    @Override
    public boolean checkBlacklistedToken(String accessToken) {
        return redisTemplate.hasKey(AUTH_LOGOUT_TOKEN_PREFIX + accessToken);
    }
}
