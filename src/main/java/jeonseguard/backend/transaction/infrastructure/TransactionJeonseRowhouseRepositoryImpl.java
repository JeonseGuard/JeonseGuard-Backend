package jeonseguard.backend.transaction.infrastructure;

import jeonseguard.backend.transaction.domain.entity.TransactionJeonseRowhouse;
import jeonseguard.backend.transaction.domain.repository.TransactionJeonseRowhouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionJeonseRowhouseRepositoryImpl implements TransactionJeonseRowhouseRepository {
    private final TransactionJeonseRowhouseJpaRepository jpaRepository;

    @Override
    public List<TransactionJeonseRowhouse> findAllByAddressAndBunAndJiAndFloorAndContractYearMonths(
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
