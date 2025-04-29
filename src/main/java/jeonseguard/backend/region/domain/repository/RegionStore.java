package jeonseguard.backend.region.domain.repository;

import java.util.Optional;

public interface RegionStore {
    void saveRegionCode(String address, String regionCode);
    void saveSigunguCode(String address, String sigunguCode);
    Optional<String> findRegionCodeByAddress(String address);
    Optional<String> findSigunguCodeByAddress(String address);
}
