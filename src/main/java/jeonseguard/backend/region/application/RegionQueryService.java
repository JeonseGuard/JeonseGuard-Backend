package jeonseguard.backend.region.application;

import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.region.domain.repository.RegionRepository;
import jeonseguard.backend.region.infrastructure.dto.RegionSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class RegionQueryService {
    private final RegionRepository regionRepository;

    @Cacheable(value = "region", key = "'region::address:' + #address")
    public RegionSummary getRegionSummary(String address) {
        return regionRepository.findByAddress(address)
                .map(RegionSummary::from)
                .orElseThrow(() -> new BusinessException(ErrorCode.REGION_SUMMARY_NOT_FOUND));
    }
}
