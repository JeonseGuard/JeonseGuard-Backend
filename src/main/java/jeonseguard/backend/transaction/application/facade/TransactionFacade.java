package jeonseguard.backend.transaction.application.facade;

import jeonseguard.backend.transaction.application.service.*;
import jeonseguard.backend.transaction.presentation.dto.request.*;
import jeonseguard.backend.transaction.presentation.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionFacade {
    private final TransactionJeonseService jeonseService;
    private final TransactionSaleService saleService;

    private static List<String> CONTRACT_YEAR_MONTHS = List.of(
            "202501", "202502", "202503", "202504"
    );

    public List<TransactionJeonseApartmentResponse> getTransactionJeonseHistoryForApartment(TransactionJeonseAddressRequest request) {
        return jeonseService.getTransactionJeonseHistoryForApartment(request, CONTRACT_YEAR_MONTHS).stream()
                .map(TransactionJeonseApartmentResponse::fromEntity)
                .toList();
    }

    public List<TransactionJeonseOfficetelResponse> getTransactionJeonseHistoryForOfficetel(TransactionJeonseAddressRequest request) {
        return jeonseService.getTransactionJeonseHistoryForOfficetel(request, CONTRACT_YEAR_MONTHS).stream()
                .map(TransactionJeonseOfficetelResponse::fromEntity)
                .toList();
    }

    public List<TransactionJeonseRowhouseResponse> getTransactionJeonseHistoryForRowhouse(TransactionJeonseAddressRequest request) {
        return jeonseService.getTransactionJeonseHistoryForRowhouse(request, CONTRACT_YEAR_MONTHS).stream()
                .map(TransactionJeonseRowhouseResponse::fromEntity)
                .toList();
    }

    public List<TransactionSaleApartmentResponse> getTransactionSaleHistoryForApartment(TransactionSaleAddressRequest request) {
        return saleService.getTransactionSaleHistoryForApartment(request, CONTRACT_YEAR_MONTHS).stream()
                .map(TransactionSaleApartmentResponse::fromEntity)
                .toList();
    }

    public List<TransactionSaleOfficetelResponse> getTransactionSaleHistoryForOfficetel(TransactionSaleAddressRequest request) {
        return saleService.getTransactionSaleHistoryForOfficetel(request, CONTRACT_YEAR_MONTHS).stream()
                .map(TransactionSaleOfficetelResponse::fromEntity)
                .toList();
    }

    public List<TransactionSaleRowhouseResponse> getTransactionSaleHistoryForRowhouse(TransactionSaleAddressRequest request) {
        return saleService.getTransactionSaleHistoryForRowhouse(request, CONTRACT_YEAR_MONTHS).stream()
                .map(TransactionSaleRowhouseResponse::fromEntity)
                .toList();
    }
}
