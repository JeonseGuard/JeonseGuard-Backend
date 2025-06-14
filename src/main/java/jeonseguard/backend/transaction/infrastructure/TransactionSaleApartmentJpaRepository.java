package jeonseguard.backend.transaction.infrastructure;

import jeonseguard.backend.transaction.domain.entity.TransactionSaleApartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionSaleApartmentJpaRepository extends JpaRepository<TransactionSaleApartment, Long> {
    List<TransactionSaleApartment> findAllByAddressAndBunAndJiAndFloorAndAreaAndContractYearMonthIn(
            String address,
            String bun,
            String ji,
            String floor,
            String area,
            List<String> contractYearMonths
    );
}
