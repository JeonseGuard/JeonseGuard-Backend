package jeonseguard.backend.jeonse.domain.repository;

import jeonseguard.backend.jeonse.infrastructure.dto.JeonseTransactionOfficetelResponse;

import java.util.List;

public interface JeonseTransactionOfficetelRepository {
    List<JeonseTransactionOfficetelResponse> findAllByAddressAndBunAndJiAndFloorAndContractYearMonths(
            String address,
            String bun,
            String ji,
            String floor,
            List<String> contractYearMonths
    );
}
