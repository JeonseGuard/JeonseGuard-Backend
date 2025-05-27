package jeonseguard.backend.transaction.infrastructure;

import jeonseguard.backend.transaction.domain.entity.TransactionSaleRowhouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionSaleRowhouseJpaRepository extends JpaRepository<TransactionSaleRowhouse, Long> {
    List<TransactionSaleRowhouse> findAllByAddressAndBunAndJiAndFloorAndAreaAndContractYearMonthIn(
            String address,
            String bun,
            String ji,
            String floor,
            String area,
            List<String> contractYearMonths
    );
}
