package jeonseguard.backend.jeonse.infrastructure.repository;

import jeonseguard.backend.jeonse.domain.entity.JeonseTransactionOfficetel;
import jeonseguard.backend.jeonse.domain.repository.JeonseTransactionOfficetelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JeonseTransactionOfficetelRepositoryImpl implements JeonseTransactionOfficetelRepository {
    private final JeonseTransactionOfficetelJpaRepository jpaRepository;

    @Override
    public List<JeonseTransactionOfficetel> findAllByAddressAndBunAndJiAndFloorAndContractYearMonths(
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
