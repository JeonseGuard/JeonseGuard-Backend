package jeonseguard.backend.transaction.infrastructure;

import jeonseguard.backend.transaction.domain.entity.TransactionSaleOfficetel;
import jeonseguard.backend.transaction.domain.repository.TransactionSaleOfficetelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionSaleOfficetelRepositoryImpl implements TransactionSaleOfficetelRepository {
    private final TransactionSaleOfficetelJpaRepository jpaRepository;

    @Override
    public List<TransactionSaleOfficetel> findAllByAddressAndBunAndJiAndFloorAndContractYearMonths(
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
