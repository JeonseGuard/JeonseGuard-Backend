package jeonseguard.backend.transaction.domain.repository;

import jeonseguard.backend.transaction.domain.entity.TransactionJeonseRowhouse;

import java.util.List;

public interface TransactionJeonseRowhouseRepository {
    List<TransactionJeonseRowhouse> findAllByAddressAndBunAndJiAndFloorAndAreaAndContractYearMonths(
            String address,
            String bun,
            String ji,
            String floor,
            String area,
            List<String> contractYearMonths
    );
}
