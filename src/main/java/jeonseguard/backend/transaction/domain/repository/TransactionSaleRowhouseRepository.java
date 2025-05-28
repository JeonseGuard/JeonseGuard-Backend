package jeonseguard.backend.transaction.domain.repository;

import jeonseguard.backend.transaction.domain.entity.TransactionSaleRowhouse;

import java.util.List;

public interface TransactionSaleRowhouseRepository {
    List<TransactionSaleRowhouse> findAllByAddressAndBunAndJiAndFloorAndAreaAndContractYearMonths(
            String address,
            String bun,
            String ji,
            String floor,
            String area,
            List<String> contractYearMonths
    );
}
