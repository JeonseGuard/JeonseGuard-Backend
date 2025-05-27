package jeonseguard.backend.transaction.application.facade;

import jeonseguard.backend.transaction.application.service.*;
import jeonseguard.backend.transaction.presentation.dto.request.TransactionAddressRequest;
import jeonseguard.backend.transaction.presentation.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionFacade {
    private final TransactionJeonseQueryService transactionJeonseQueryService;
    private final TransactionSaleQueryService transactionSaleQueryService;

    public List<TransactionJeonseApartmentResponse> getTransactionJeonseHistoryForApartment(TransactionAddressRequest request) {
        return transactionJeonseQueryService.getTransactionJeonseHistoryForApartment(request).stream()
                .map(TransactionJeonseApartmentResponse::from)
                .toList();
    }

    public List<TransactionJeonseOfficetelResponse> getTransactionJeonseHistoryForOfficetel(TransactionAddressRequest request) {
        return transactionJeonseQueryService.getTransactionJeonseHistoryForOfficetel(request).stream()
                .map(TransactionJeonseOfficetelResponse::from)
                .toList();
    }

    public List<TransactionJeonseRowhouseResponse> getTransactionJeonseHistoryForRowhouse(TransactionAddressRequest request) {
        return transactionJeonseQueryService.getTransactionJeonseHistoryForRowhouse(request).stream()
                .map(TransactionJeonseRowhouseResponse::from)
                .toList();
    }

    public List<TransactionSaleApartmentResponse> getTransactionSaleHistoryForApartment(TransactionAddressRequest request) {
        return transactionSaleQueryService.getTransactionSaleHistoryForApartment(request).stream()
                .map(TransactionSaleApartmentResponse::from)
                .toList();
    }

    public List<TransactionSaleOfficetelResponse> getTransactionSaleHistoryForOfficetel(TransactionAddressRequest request) {
        return transactionSaleQueryService.getTransactionSaleHistoryForOfficetel(request).stream()
                .map(TransactionSaleOfficetelResponse::from)
                .toList();
    }

    public List<TransactionSaleRowhouseResponse> getTransactionSaleHistoryForRowhouse(TransactionAddressRequest request) {
        return transactionSaleQueryService.getTransactionSaleHistoryForRowhouse(request).stream()
                .map(TransactionSaleRowhouseResponse::from)
                .toList();
    }
}
