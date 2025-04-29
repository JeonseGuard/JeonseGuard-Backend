package jeonseguard.backend.region.infrastructure;

import jeonseguard.backend.region.domain.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionJpaRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByAddress(String address);
}
