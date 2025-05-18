package jeonseguard.backend.jeonse.infrastructure.repository;

import jeonseguard.backend.jeonse.domain.entity.JeonseTransactionRowhouse;
import jeonseguard.backend.jeonse.domain.repository.JeonseTransactionRowhouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JeonseTransactionRowhouseRepositoryImpl implements JeonseTransactionRowhouseRepository {
    private final JeonseTransactionRowhouseJpaRepository jpaRepository;

    @Override
    public List<JeonseTransactionRowhouse> findAllByAddressAndBunAndJiAndFloorAndContractYearMonths(
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
