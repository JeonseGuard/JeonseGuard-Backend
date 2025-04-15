package jeonseguard.backend.region.application.service;

import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.region.domain.entity.Region;
import jeonseguard.backend.region.domain.repository.*;
import jeonseguard.backend.region.presentation.dto.DeleteRegionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;
    private final RegionStore regionStore;

    @Transactional(readOnly = true)
    public String getRegionCode(String address) {
        return regionStore.findRegionCodeByAddress(address)
                .orElseGet(() -> getAndCacheRegionCode(address));
    }

    @Transactional(readOnly = true)
    public String getSigunguCode(String address) {
        return regionStore.findSigunguCodeByAddress(address)
                .orElseGet(() -> getAndCacheSigunguCode(address));
    }

    @Transactional
    public void deleteRegion(DeleteRegionRequest request) {
        regionStore.deleteByAddress(request.address());
        regionRepository.deleteByAddress(request.address());
    }

    private String getAndCacheRegionCode(String address) {
        Region region = getRegion(address);
        regionStore.saveRegionCode(region.getAddress(), region.getRegionCode());
        return region.getRegionCode();
    }

    private String getAndCacheSigunguCode(String address) {
        Region region = getRegion(address);
        regionStore.saveSigunguCode(region.getAddress(), region.getSigunguCode());
        return region.getSigunguCode();
    }

    private Region getRegion(String address) {
        return regionRepository.findByAddress(address)
                .orElseThrow(() -> new BusinessException(ErrorCode.REGION_NOT_FOUND));
    }
}
