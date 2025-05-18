package jeonseguard.backend.jeonse.infrastructure.repository;

import jeonseguard.backend.jeonse.domain.entity.JeonseTransactionOfficetel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JeonseTransactionOfficetelJpaRepository extends JpaRepository<JeonseTransactionOfficetel, Long> {
    List<JeonseTransactionOfficetel> findAllByAddressAndBunAndJiAndFloorAndContractYearMonthIn(
            String address,
            String bun,
            String ji,
            String floor,
            List<String> contractYearMonths
    );
}
