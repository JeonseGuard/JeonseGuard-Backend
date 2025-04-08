package jeonseguard.backend.regioncode.infrastructure.repository;

import jeonseguard.backend.regioncode.domain.entity.RegionCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionCodeJpaRepository extends JpaRepository<RegionCode, Long> {
    Optional<RegionCode> findByCode(String code);
    boolean existsByCode(String code);
}
