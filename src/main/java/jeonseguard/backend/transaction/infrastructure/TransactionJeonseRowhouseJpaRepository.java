package jeonseguard.backend.transaction.infrastructure;

import jeonseguard.backend.transaction.domain.entity.TransactionJeonseRowhouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionJeonseRowhouseJpaRepository extends JpaRepository<TransactionJeonseRowhouse, Long> {
    List<TransactionJeonseRowhouse> findAllByAddressAndBunAndJiAndFloorAndContractYearMonthIn(
            String address,
            String bun,
            String ji,
            String floor,
            List<String> contractYearMonths
    );
}
