package jeonseguard.backend.jeonse.domain.repository;

import jeonseguard.backend.jeonse.infrastructure.dto.JeonseTransactionRowhouseResponse;

import java.util.List;

public interface JeonseTransactionRowhouseRepository {
    List<JeonseTransactionRowhouseResponse> findAllByAddressAndBunAndJiAndFloorAndContractYearMonths(
            String address,
            String bun,
            String ji,
            String floor,
            List<String> contractYearMonths
    );
}
