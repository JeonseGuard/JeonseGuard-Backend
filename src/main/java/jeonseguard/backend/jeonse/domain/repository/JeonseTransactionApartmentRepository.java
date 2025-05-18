package jeonseguard.backend.jeonse.domain.repository;

import jeonseguard.backend.jeonse.domain.entity.JeonseTransactionApartment;

import java.util.List;

public interface JeonseTransactionApartmentRepository {
    List<JeonseTransactionApartment> findAllByAddressAndBunAndJiAndFloorAndContractYearMonths(
            String address,
            String bun,
            String ji,
            String floor,
            List<String> contractYearMonths
    );
}
