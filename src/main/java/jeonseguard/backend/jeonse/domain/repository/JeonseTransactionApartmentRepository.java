package jeonseguard.backend.jeonse.domain.repository;

import jeonseguard.backend.jeonse.infrastructure.dto.JeonseTransactionApartmentResponse;

import java.util.List;

public interface JeonseTransactionApartmentRepository {
    List<JeonseTransactionApartmentResponse> findAllByAddressAndBunAndJiAndFloorAndContractYearMonths(
            String address,
            String bun,
            String ji,
            String floor,
            List<String> contractYearMonths
    );
}
