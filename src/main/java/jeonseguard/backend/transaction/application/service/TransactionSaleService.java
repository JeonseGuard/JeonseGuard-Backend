package jeonseguard.backend.transaction.application.service;

import jeonseguard.backend.global.config.properties.TransactionProperties;
import jeonseguard.backend.transaction.domain.entity.*;
import jeonseguard.backend.transaction.domain.repository.*;
import jeonseguard.backend.transaction.presentation.dto.request.TransactionSaleAddressRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionSaleService {
    private final TransactionSaleApartmentRepository apartmentRepository;
    private final TransactionSaleOfficetelRepository officetelRepository;
    private final TransactionSaleRowhouseRepository rowhouseRepository;
    private final TransactionProperties properties;

    @Cacheable(cacheNames = "transactionSaleApartment", key = "#request.address() + ':' + #request.bun() + ':' + #request.ji() + ':' + #request.floorNumber()")
    @Transactional(readOnly = true)
    public List<TransactionSaleApartment> getTransactionSaleHistoryForApartment(TransactionSaleAddressRequest request) {
        return apartmentRepository.findAllByAddressAndBunAndJiAndFloorAndContractYearMonths(
                request.address(),
                request.bun(),
                request.ji(),
                request.floorNumber(),
                properties.contractYearMonths()
        );
    }

    @Cacheable(cacheNames = "transactionSaleOfficetel", key = "#request.address() + ':' + #request.bun() + ':' + #request.ji() + ':' + #request.floorNumber()")
    @Transactional(readOnly = true)
    public List<TransactionSaleOfficetel> getTransactionSaleHistoryForOfficetel(TransactionSaleAddressRequest request) {
        return officetelRepository.findAllByAddressAndBunAndJiAndFloorAndContractYearMonths(
                request.address(),
                request.bun(),
                request.ji(),
                request.floorNumber(),
                properties.contractYearMonths()
        );
    }

    @Cacheable(cacheNames = "transactionSaleRowhouse", key = "#request.address() + ':' + #request.bun() + ':' + #request.ji() + ':' + #request.floorNumber()")
    @Transactional(readOnly = true)
    public List<TransactionSaleRowhouse> getTransactionSaleHistoryForRowhouse(TransactionSaleAddressRequest request) {
        return rowhouseRepository.findAllByAddressAndBunAndJiAndFloorAndContractYearMonths(
                request.address(),
                request.bun(),
                request.ji(),
                request.floorNumber(),
                properties.contractYearMonths()
        );
    }
}
