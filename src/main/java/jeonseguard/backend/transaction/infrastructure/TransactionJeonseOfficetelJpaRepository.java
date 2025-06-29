package jeonseguard.backend.transaction.infrastructure;

import jeonseguard.backend.transaction.domain.entity.TransactionJeonseOfficetel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionJeonseOfficetelJpaRepository extends JpaRepository<TransactionJeonseOfficetel, Long> {
    List<TransactionJeonseOfficetel> findAllByAddressAndBunAndJiAndFloorAndAreaAndContractYearMonthIn(
            String address,
            String bun,
            String ji,
            String floor,
            String area,
            List<String> contractYearMonths
    );
}
