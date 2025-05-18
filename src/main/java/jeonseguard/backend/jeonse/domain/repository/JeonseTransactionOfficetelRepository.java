package jeonseguard.backend.jeonse.domain.repository;

import jeonseguard.backend.jeonse.domain.entity.JeonseTransactionOfficetel;

import java.util.List;

public interface JeonseTransactionOfficetelRepository {
    List<JeonseTransactionOfficetel> findAllByAddressAndBunAndJiAndFloorAndContractYearMonths(
            String address,
            String bun,
            String ji,
            String floor,
            List<String> contractYearMonths
    );
}
