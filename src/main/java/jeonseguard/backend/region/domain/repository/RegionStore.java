package jeonseguard.backend.region.domain.repository;

import jeonseguard.backend.region.domain.entity.Region;

import java.util.Optional;

public interface RegionStore {
    Optional<Region> findByAddress(String address);
    void save(Region region);
    void deleteByAddress(String address);
}
