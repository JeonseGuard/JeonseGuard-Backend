package jeonseguard.backend.transaction.infrastructure;

import jeonseguard.backend.transaction.domain.entity.TransactionJeonseApartment;
import jeonseguard.backend.transaction.domain.repository.TransactionJeonseApartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionJeonseApartmentRepositoryImpl implements TransactionJeonseApartmentRepository {
    private final TransactionJeonseApartmentJpaRepository jpaRepository;

    @Override
    public List<TransactionJeonseApartment> findAllByAddressAndBunAndJiAndFloorAndAreaAndContractYearMonths(
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
