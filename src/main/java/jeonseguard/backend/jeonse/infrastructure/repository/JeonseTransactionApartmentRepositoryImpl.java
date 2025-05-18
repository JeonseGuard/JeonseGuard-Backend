package jeonseguard.backend.jeonse.infrastructure.repository;

import jeonseguard.backend.jeonse.domain.entity.JeonseTransactionApartment;
import jeonseguard.backend.jeonse.domain.repository.JeonseTransactionApartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JeonseTransactionApartmentRepositoryImpl implements JeonseTransactionApartmentRepository {
    private final JeonseTransactionApartmentJpaRepository jpaRepository;

    @Override
    public List<JeonseTransactionApartment> findAllByAddressAndBunAndJiAndFloorAndContractYearMonths(
            String address,
            String bun,
            String ji,
            String floor,
            List<String> contractYearMonths
    ) {
        return jpaRepository.findAllByAddressAndBunAndJiAndFloorAndContractYearMonthIn(
                address,
                bun,
                ji,
                floor,
                contractYearMonths
        );
    }
}
