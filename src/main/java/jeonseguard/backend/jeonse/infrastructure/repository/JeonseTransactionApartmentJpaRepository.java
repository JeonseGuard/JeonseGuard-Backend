package jeonseguard.backend.jeonse.infrastructure.repository;

import jeonseguard.backend.jeonse.domain.entity.JeonseTransactionApartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JeonseTransactionApartmentJpaRepository extends JpaRepository<JeonseTransactionApartment, Long> {
    List<JeonseTransactionApartment> findAllByAddressAndBunAndJiAndFloorAndContractYearMonthIn(
            String address,
            String bun,
            String ji,
            String floor,
            List<String> contractYearMonths
    );
}
