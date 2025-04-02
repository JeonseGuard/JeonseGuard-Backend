package jeonseguard.backend.region.domain;

import java.util.Optional;

public interface RegionCodeRepository {
    Optional<RegionCode> findByCode(String code);
}
