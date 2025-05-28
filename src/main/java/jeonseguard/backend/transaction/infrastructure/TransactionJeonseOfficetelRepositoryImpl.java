package jeonseguard.backend.transaction.infrastructure;

import jeonseguard.backend.transaction.domain.entity.TransactionJeonseOfficetel;
import jeonseguard.backend.transaction.domain.repository.TransactionJeonseOfficetelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionJeonseOfficetelRepositoryImpl implements TransactionJeonseOfficetelRepository {
    private final TransactionJeonseOfficetelJpaRepository jpaRepository;

    @Override
    public List<TransactionJeonseOfficetel> findAllByAddressAndBunAndJiAndFloorAndAreaAndContractYearMonths(
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
