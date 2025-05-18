package jeonseguard.backend.jeonse.infrastructure.repository;

import jeonseguard.backend.jeonse.domain.entity.JeonseTransactionRowhouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JeonseTransactionRowhouseJpaRepository extends JpaRepository<JeonseTransactionRowhouse, Long> {
    List<JeonseTransactionRowhouse> findAllByAddressAndBunAndJiAndFloorAndContractYearMonthIn(
            String address,
            String bun,
            String ji,
            String floor,
            List<String> contractYearMonths
    );
}
