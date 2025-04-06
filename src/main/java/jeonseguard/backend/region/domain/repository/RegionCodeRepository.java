package jeonseguard.backend.region.domain.repository;

import jeonseguard.backend.region.domain.entity.RegionCode;

import java.util.Optional;

public interface RegionCodeRepository {
    Optional<RegionCode> findByCode(String code);
}
