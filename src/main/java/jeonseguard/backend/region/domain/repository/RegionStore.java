package jeonseguard.backend.region.domain.repository;

import jeonseguard.backend.region.domain.entity.Region;

import java.util.Optional;

public interface RegionStore {
    void save(Region region);
    Optional<Region> findByAddress(String address);
    void deleteByAddress(String address);
}
