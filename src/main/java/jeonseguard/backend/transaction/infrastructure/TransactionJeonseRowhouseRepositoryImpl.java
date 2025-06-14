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
    public List<TransactionJeonseRowhouse> findAllByAddressAndBunAndJiAndFloorAndAreaAndContractYearMonths(
            String address,
            String bun,
            String ji,
            String floor,
            String area,
            List<String> contractYearMonths
    ) {
        return jpaRepository.findAllByAddressAndBunAndJiAndFloorAndAreaAndContractYearMonthIn(
                address,
                bun,
                ji,
                floor,
                area,
                contractYearMonths
        );
    }
}
