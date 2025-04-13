package jeonseguard.backend.region.domain.repository;

import jeonseguard.backend.region.domain.entity.Region;

import java.util.Optional;

public interface RegionRepository {
    Optional<Region> findByAddress(String address);
    void deleteByAddress(String address);
}
