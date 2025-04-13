package jeonseguard.backend.region.infrastructure;

import jeonseguard.backend.region.domain.entity.Region;
import jeonseguard.backend.region.domain.repository.RegionStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RegionRedisRepository implements RegionStore {
    private final RedisTemplate<String, Region> redisTemplate;

    private static final String KEY_PREFIX = "region:";
    private static final long CACHE_EXPIRATION_HOURS = 6L;

    @Override
    public void save(Region region) {
        redisTemplate.opsForValue().set(KEY_PREFIX + region.getAddress(), region, CACHE_EXPIRATION_HOURS, TimeUnit.HOURS);
    }

    @Override
    public Optional<Region> findByAddress(String address) {
        Region region = redisTemplate.opsForValue().get(KEY_PREFIX + address);
        return Optional.ofNullable(region);
    }

    @Override
    public void deleteByAddress(String address) {
        redisTemplate.delete(KEY_PREFIX + address);
    }
}
