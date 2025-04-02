package jeonseguard.backend.region.infrastructure;

import jeonseguard.backend.region.domain.RegionCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionCodeJpaRepository extends JpaRepository<RegionCode, Long> {
    Optional<RegionCode> findByCode(String code);
}
