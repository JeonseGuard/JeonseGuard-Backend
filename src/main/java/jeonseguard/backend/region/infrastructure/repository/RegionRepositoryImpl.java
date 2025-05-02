package jeonseguard.backend.region.infrastructure.repository;

import jeonseguard.backend.region.domain.entity.Region;
import jeonseguard.backend.region.domain.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RegionRepositoryImpl implements RegionRepository {
    private final RegionJpaRepository jpaRepository;

    @Override
    public void save(Region region) {
        jpaRepository.save(region);
    }

    @Override
    public Optional<Region> findByAddress(String address) {
        return jpaRepository.findByAddress(address);
    }
}
