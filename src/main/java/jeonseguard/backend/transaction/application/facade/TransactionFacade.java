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

    public List<TransactionJeonseApartmentResponse> getTransactionJeonseHistoryForApartment(TransactionJeonseAddressRequest request) {
        return jeonseService.getTransactionJeonseHistoryForApartment(request).stream()
                .map(TransactionJeonseApartmentResponse::fromEntity)
                .toList();
    }

    public List<TransactionJeonseOfficetelResponse> getTransactionJeonseHistoryForOfficetel(TransactionJeonseAddressRequest request) {
        return jeonseService.getTransactionJeonseHistoryForOfficetel(request).stream()
                .map(TransactionJeonseOfficetelResponse::fromEntity)
                .toList();
    }

    public List<TransactionJeonseRowhouseResponse> getTransactionJeonseHistoryForRowhouse(TransactionJeonseAddressRequest request) {
        return jeonseService.getTransactionJeonseHistoryForRowhouse(request).stream()
                .map(TransactionJeonseRowhouseResponse::fromEntity)
                .toList();
    }

    public List<TransactionSaleApartmentResponse> getTransactionSaleHistoryForApartment(TransactionSaleAddressRequest request) {
        return saleService.getTransactionSaleHistoryForApartment(request).stream()
                .map(TransactionSaleApartmentResponse::fromEntity)
                .toList();
    }

    public List<TransactionSaleOfficetelResponse> getTransactionSaleHistoryForOfficetel(TransactionSaleAddressRequest request) {
        return saleService.getTransactionSaleHistoryForOfficetel(request).stream()
                .map(TransactionSaleOfficetelResponse::fromEntity)
                .toList();
    }

    public List<TransactionSaleRowhouseResponse> getTransactionSaleHistoryForRowhouse(TransactionSaleAddressRequest request) {
        return saleService.getTransactionSaleHistoryForRowhouse(request).stream()
                .map(TransactionSaleRowhouseResponse::fromEntity)
                .toList();
    }
}
