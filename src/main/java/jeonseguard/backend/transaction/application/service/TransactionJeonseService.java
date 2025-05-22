package jeonseguard.backend.transaction.application.service;

import jeonseguard.backend.global.config.properties.TransactionProperties;
import jeonseguard.backend.transaction.domain.entity.*;
import jeonseguard.backend.transaction.domain.repository.*;
import jeonseguard.backend.transaction.presentation.dto.request.TransactionJeonseAddressRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionJeonseService {
    private final TransactionJeonseApartmentRepository apartmentRepository;
    private final TransactionJeonseOfficetelRepository officetelRepository;
    private final TransactionJeonseRowhouseRepository rowhouseRepository;
    private final TransactionProperties properties;

    @Cacheable(cacheNames = "transactionJeonseApartment", key = "#request.address() + ':' + #request.bun() + ':' + #request.ji() + ':' + #request.floorNumber()")
    @Transactional(readOnly = true)
    public List<TransactionJeonseApartment> getTransactionJeonseHistoryForApartment(TransactionJeonseAddressRequest request) {
        return apartmentRepository.findAllByAddressAndBunAndJiAndFloorAndContractYearMonths(
                request.address(),
                request.bun(),
                request.ji(),
                request.floorNumber(),
                properties.contractYearMonths()
        );
    }

    @Cacheable(cacheNames = "transactionJeonseOfficetel", key = "#request.address() + ':' + #request.bun() + ':' + #request.ji() + ':' + #request.floorNumber()")
    @Transactional(readOnly = true)
    public List<TransactionJeonseOfficetel> getTransactionJeonseHistoryForOfficetel(TransactionJeonseAddressRequest request) {
        return officetelRepository.findAllByAddressAndBunAndJiAndFloorAndContractYearMonths(
                request.address(),
                request.bun(),
                request.ji(),
                request.floorNumber(),
                properties.contractYearMonths()
        );
    }

    @Cacheable(cacheNames = "transactionJeonseRowhouse", key = "#request.address() + ':' + #request.bun() + ':' + #request.ji() + ':' + #request.floorNumber()")
    @Transactional(readOnly = true)
    public List<TransactionJeonseRowhouse> getTransactionJeonseHistoryForRowhouse(TransactionJeonseAddressRequest request) {
        return rowhouseRepository.findAllByAddressAndBunAndJiAndFloorAndContractYearMonths(
                request.address(),
                request.bun(),
                request.ji(),
                request.floorNumber(),
                properties.contractYearMonths()
        );
    }
}
