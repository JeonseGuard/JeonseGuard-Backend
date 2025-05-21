package jeonseguard.backend.transaction.domain.repository;

import jeonseguard.backend.transaction.domain.entity.TransactionSaleOfficetel;

import java.util.List;

public interface TransactionSaleOfficetelRepository {
    List<TransactionSaleOfficetel> findAllByAddressAndBunAndJiAndFloorAndContractYearMonths(
            String address,
            String bun,
            String ji,
            String floor,
            List<String> contractYearMonths
    );
}
