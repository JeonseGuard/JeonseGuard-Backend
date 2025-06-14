package jeonseguard.backend.transaction.infrastructure;

import jeonseguard.backend.transaction.domain.entity.TransactionSaleRowhouse;
import jeonseguard.backend.transaction.domain.repository.TransactionSaleRowhouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionSaleRowhouseRepositoryImpl implements TransactionSaleRowhouseRepository {
    private final TransactionSaleRowhouseJpaRepository jpaRepository;

    @Override
    public List<TransactionSaleRowhouse> findAllByAddressAndBunAndJiAndFloorAndAreaAndContractYearMonths(
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
