package jeonseguard.backend.transaction.infrastructure;

import jeonseguard.backend.transaction.domain.entity.TransactionJeonseApartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionJeonseApartmentJpaRepository extends JpaRepository<TransactionJeonseApartment, Long> {
    List<TransactionJeonseApartment> findAllByAddressAndBunAndJiAndFloorAndAreaAndContractYearMonthIn(
            String address,
            String bun,
            String ji,
            String floor,
            String area,
            List<String> contractYearMonths
    );
}
