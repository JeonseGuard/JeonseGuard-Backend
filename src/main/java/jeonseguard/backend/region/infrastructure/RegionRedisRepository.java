package jeonseguard.backend.region.infrastructure;

import jeonseguard.backend.region.domain.repository.RegionStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RegionRedisRepository implements RegionStore {
    private final RedisTemplate<String, String> redisTemplate;

    private static final String REGION_CODE_KEY_PREFIX = "region-code:";
    private static final String SIGUNGU_CODE_KEY_PREFIX = "sigungu-code:";
    private static final long CACHE_EXPIRATION_HOURS = 6L;

    @Override
    public void saveRegionCode(String address, String regionCode) {
        redisTemplate.opsForValue().set(REGION_CODE_KEY_PREFIX + address, regionCode, CACHE_EXPIRATION_HOURS, TimeUnit.HOURS);
    }

    @Override
    public void saveSigunguCode(String address, String sigunguCode) {
        redisTemplate.opsForValue().set(SIGUNGU_CODE_KEY_PREFIX + address, sigunguCode, CACHE_EXPIRATION_HOURS, TimeUnit.HOURS);
    }

    @Override
    public Optional<String> findRegionCodeByAddress(String address) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(REGION_CODE_KEY_PREFIX + address));
    }

    @Override
    public Optional<String> findSigunguCodeByAddress(String address) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(SIGUNGU_CODE_KEY_PREFIX + address));
    }
}
