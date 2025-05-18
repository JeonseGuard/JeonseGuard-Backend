package jeonseguard.backend.jeonse.domain.repository;

import jeonseguard.backend.jeonse.domain.entity.JeonseTransactionRowhouse;

import java.util.List;

public interface JeonseTransactionRowhouseRepository {
    List<JeonseTransactionRowhouse> findAllByAddressAndBunAndJiAndFloorAndContractYearMonths(
            String address,
            String bun,
            String ji,
            String floor,
            List<String> contractYearMonths
    );
}
