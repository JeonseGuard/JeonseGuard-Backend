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

    private static final String LOGOUT_KEY_PREFIX = "blacklist:";
    private static final String TOKEN_BLACKLISTED_VALUE = "LOGOUT";

    @Override
    public void blacklistToken(String accessToken, long accessTokenExpirationTime) {
        redisTemplate.opsForValue().set(LOGOUT_KEY_PREFIX + accessToken, TOKEN_BLACKLISTED_VALUE, accessTokenExpirationTime, TimeUnit.SECONDS);
    }

    @Override
    public boolean checkBlacklistedToken(String accessToken) {
        return redisTemplate.hasKey(LOGOUT_KEY_PREFIX + accessToken);
    }
}
