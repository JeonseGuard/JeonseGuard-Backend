package jeonseguard.backend.regioncode.domain.repository;

import jeonseguard.backend.regioncode.domain.entity.RegionCode;

import java.util.*;

public interface RegionCodeRepository {
    Optional<RegionCode> findByCode(String code);
    boolean existsByCode(String code);
    void saveAll(List<RegionCode> regionCodes);
}
