package jeonseguard.backend.region.infrastructure.repository;

import jeonseguard.backend.region.domain.entity.RegionCode;
import jeonseguard.backend.region.domain.repository.RegionCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RegionCodeRepositoryImpl implements RegionCodeRepository {
    private final RegionCodeJpaRepository jpaRepository;

    @Override
    public Optional<RegionCode> findByCode(String code) {
        return jpaRepository.findByCode(code);
    }
}
