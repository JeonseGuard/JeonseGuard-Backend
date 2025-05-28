package jeonseguard.backend.transaction.domain.repository;

import jeonseguard.backend.transaction.domain.entity.TransactionJeonseApartment;

import java.util.List;

public interface TransactionJeonseApartmentRepository {
    List<TransactionJeonseApartment> findAllByAddressAndBunAndJiAndFloorAndAreaAndContractYearMonths(
            String address,
            String bun,
            String ji,
            String floor,
            String area,
            List<String> contractYearMonths
    );
}
