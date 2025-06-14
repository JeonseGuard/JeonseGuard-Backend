package jeonseguard.backend.transaction.infrastructure;

import jeonseguard.backend.transaction.domain.entity.TransactionSaleOfficetel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionSaleOfficetelJpaRepository extends JpaRepository<TransactionSaleOfficetel, Long> {
    List<TransactionSaleOfficetel> findAllByAddressAndBunAndJiAndFloorAndAreaAndContractYearMonthIn(
            String address,
            String bun,
            String ji,
            String floor,
            String area,
            List<String> contractYearMonths
    );
}
