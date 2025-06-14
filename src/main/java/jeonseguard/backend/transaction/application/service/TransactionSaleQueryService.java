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
import static jeonseguard.backend.global.util.TransactionPriceUtil.filterByHighestPricePerMonth;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class TransactionSaleQueryService {
    private final TransactionSaleApartmentRepository apartmentRepository;
    private final TransactionSaleOfficetelRepository officetelRepository;
    private final TransactionSaleRowhouseRepository rowhouseRepository;
    private final TransactionProperties properties;

    @Cacheable(value = "transactionSaleApartment", key = "'" + TRANSACTION_SALE_APARTMENT_PREFIX + "' + #request.toCacheKey()")
    public List<TransactionSaleApartment> getTransactionSaleHistoryForApartment(TransactionAddressRequest request) {
        List<TransactionSaleApartment> all = apartmentRepository.findAllByAddressAndBunAndJiAndFloorAndAreaAndContractYearMonths(
                request.address(),
                request.bun(),
                request.ji(),
                request.floorNumber(),
                request.area(),
                properties.contractYearMonths()
        );
        return filterByHighestPricePerMonth(
                all,
                TransactionSaleApartment::getContractYearMonth,
                TransactionSaleApartment::getPrice
        );
    }

    @Cacheable(value = "transactionSaleOfficetel", key = "'" + TRANSACTION_SALE_OFFICETEL_PREFIX + "' + #request.toCacheKey()")
    public List<TransactionSaleOfficetel> getTransactionSaleHistoryForOfficetel(TransactionAddressRequest request) {
        List<TransactionSaleOfficetel> all = officetelRepository.findAllByAddressAndBunAndJiAndFloorAndAreaAndContractYearMonths(
                request.address(),
                request.bun(),
                request.ji(),
                request.floorNumber(),
                request.area(),
                properties.contractYearMonths()
        );
        return filterByHighestPricePerMonth(
                all,
                TransactionSaleOfficetel::getContractYearMonth,
                TransactionSaleOfficetel::getPrice
        );
    }

    @Cacheable(value = "transactionSaleRowhouse", key = "'" + TRANSACTION_SALE_ROWHOUSE_PREFIX + "' + #request.toCacheKey()")
    public List<TransactionSaleRowhouse> getTransactionSaleHistoryForRowhouse(TransactionAddressRequest request) {
        List<TransactionSaleRowhouse> all = rowhouseRepository.findAllByAddressAndBunAndJiAndFloorAndAreaAndContractYearMonths(
                request.address(),
                request.bun(),
                request.ji(),
                request.floorNumber(),
                request.area(),
                properties.contractYearMonths()
        );
        return filterByHighestPricePerMonth(
                all,
                TransactionSaleRowhouse::getContractYearMonth,
                TransactionSaleRowhouse::getPrice
        );
    }
}
