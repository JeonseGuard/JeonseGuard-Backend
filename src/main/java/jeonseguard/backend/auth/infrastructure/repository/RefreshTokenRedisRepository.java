package jeonseguard.backend.auth.infrastructure.repository;

import jeonseguard.backend.auth.domain.repository.RefreshTokenStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRedisRepository implements RefreshTokenStore {
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void saveRefreshToken(Long userId, String refreshToken, long refreshExpirationTime) {
        redisTemplate.opsForValue().set("refresh:" + userId, refreshToken, refreshExpirationTime, TimeUnit.SECONDS);
    }

    @Override
    public String getRefreshToken(Long userId) {
        return redisTemplate.opsForValue().get("refresh:" + userId);
    }

    @Override
    public void removeRefreshToken(Long userId) {
        redisTemplate.delete("refresh:" + userId);
    }
}
