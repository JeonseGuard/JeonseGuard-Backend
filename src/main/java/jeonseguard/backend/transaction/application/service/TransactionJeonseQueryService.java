package jeonseguard.backend.transaction.application.service;

import jeonseguard.backend.global.config.properties.TransactionProperties;
import jeonseguard.backend.transaction.domain.entity.*;
import jeonseguard.backend.transaction.domain.repository.*;
import jeonseguard.backend.transaction.presentation.dto.request.TransactionAddressRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static jeonseguard.backend.global.constant.CacheKey.*;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class TransactionJeonseQueryService {
    private final TransactionJeonseApartmentRepository apartmentRepository;
    private final TransactionJeonseOfficetelRepository officetelRepository;
    private final TransactionJeonseRowhouseRepository rowhouseRepository;
    private final TransactionProperties properties;

    @Cacheable(value = "transactionJeonseApartment", key = "'" + TRANSACTION_JEONSE_APARTMENT_PREFIX + "' + #request.toCacheKey()")
    public List<TransactionJeonseApartment> getTransactionJeonseHistoryForApartment(TransactionAddressRequest request) {
        return apartmentRepository.findAllByAddressAndBunAndJiAndFloorAndAreaAndContractYearMonths(
                request.address(),
                request.bun(),
                request.ji(),
                request.floorNumber(),
                request.area(),
                properties.contractYearMonths()
        );
    }

    @Cacheable(value = "transactionJeonseOfficetel", key = "'" + TRANSACTION_JEONSE_OFFICETEL_PREFIX + "' + #request.toCacheKey()")
    public List<TransactionJeonseOfficetel> getTransactionJeonseHistoryForOfficetel(TransactionAddressRequest request) {
        return officetelRepository.findAllByAddressAndBunAndJiAndFloorAndAreaAndContractYearMonths(
                request.address(),
                request.bun(),
                request.ji(),
                request.floorNumber(),
                request.area(),
                properties.contractYearMonths()
        );
    }

    @Cacheable(value = "transactionJeonseRowhouse", key = "'" + TRANSACTION_JEONSE_ROWHOUSE_PREFIX + "' + #request.toCacheKey()")
    public List<TransactionJeonseRowhouse> getTransactionJeonseHistoryForRowhouse(TransactionAddressRequest request) {
        return rowhouseRepository.findAllByAddressAndBunAndJiAndFloorAndAreaAndContractYearMonths(
                request.address(),
                request.bun(),
                request.ji(),
                request.floorNumber(),
                request.area(),
                properties.contractYearMonths()
        );
    }
}
