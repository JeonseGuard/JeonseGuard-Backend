package jeonseguard.backend.region.application.service;

import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.region.domain.entity.Region;
import jeonseguard.backend.region.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;
    private final RegionStore regionStore;

    @Transactional(readOnly = true)
    public Region getRegionByAddress(String address) {
        return regionStore.findByAddress(address)
                .or(() -> regionRepository.findByAddress(address)
                        .map(region -> {
                            regionStore.save(region);
                            return region;
                        }))
                .orElseThrow(() -> new BusinessException(ErrorCode.REGION_NOT_FOUND));
    }

    @Transactional
    public void deleteRegionByAddress(String address) {
        regionStore.deleteByAddress(address);
        regionRepository.deleteByAddress(address);
    }
}
