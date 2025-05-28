package jeonseguard.backend.transaction.domain.repository;

import jeonseguard.backend.transaction.domain.entity.TransactionSaleApartment;

import java.util.List;

public interface TransactionSaleApartmentRepository {
    List<TransactionSaleApartment> findAllByAddressAndBunAndJiAndFloorAndAreaAndContractYearMonths(
            String address,
            String bun,
            String ji,
            String floor,
            String area,
            List<String> contractYearMonths
    );
}
