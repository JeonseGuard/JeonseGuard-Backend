package jeonseguard.backend.transaction.application.facade;

import jeonseguard.backend.transaction.application.service.*;
import jeonseguard.backend.transaction.presentation.dto.request.TransactionAddressRequest;
import jeonseguard.backend.transaction.presentation.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TransactionFacade {
    private final TransactionJeonseQueryService transactionJeonseQueryService;
    private final TransactionSaleQueryService transactionSaleQueryService;

    public TransactionSummaryApartmentResponse getTransactionSummaryHistoryForApartment(TransactionAddressRequest request) {
        List<TransactionJeonseApartmentResponse> jeonses = getTransactionJeonseHistoryForApartment(request);
        List<TransactionSaleApartmentResponse> sales = getTransactionSaleHistoryForApartment(request);
        return TransactionSummaryApartmentResponse.of(jeonses, sales);
    }

    public TransactionSummaryOfficetelResponse getTransactionSummaryHistoryForOfficetel(TransactionAddressRequest request) {
        List<TransactionJeonseOfficetelResponse> jeonses = getTransactionJeonseHistoryForOfficetel(request);
        List<TransactionSaleOfficetelResponse> sales = getTransactionSaleHistoryForOfficetel(request);
        return TransactionSummaryOfficetelResponse.of(jeonses, sales);
    }

    public TransactionSummaryRowhouseResponse getTransactionSummaryHistoryForRowhouse(TransactionAddressRequest request) {
        List<TransactionJeonseRowhouseResponse> jeonses = getTransactionJeonseHistoryForRowhouse(request);
        List<TransactionSaleRowhouseResponse> sales = getTransactionSaleHistoryForRowhouse(request);
        return TransactionSummaryRowhouseResponse.of(jeonses, sales);
    }

    public List<TransactionJeonseApartmentResponse> getTransactionJeonseHistoryForApartment(TransactionAddressRequest request) {
        return transactionJeonseQueryService.getTransactionJeonseHistoryForApartment(request).stream()
                .map(TransactionJeonseApartmentResponse::from)
                .collect(Collectors.toList());
    }

    public List<TransactionJeonseOfficetelResponse> getTransactionJeonseHistoryForOfficetel(TransactionAddressRequest request) {
        return transactionJeonseQueryService.getTransactionJeonseHistoryForOfficetel(request).stream()
                .map(TransactionJeonseOfficetelResponse::from)
                .collect(Collectors.toList());
    }

    public List<TransactionJeonseRowhouseResponse> getTransactionJeonseHistoryForRowhouse(TransactionAddressRequest request) {
        return transactionJeonseQueryService.getTransactionJeonseHistoryForRowhouse(request).stream()
                .map(TransactionJeonseRowhouseResponse::from)
                .collect(Collectors.toList());
    }

    public List<TransactionSaleApartmentResponse> getTransactionSaleHistoryForApartment(TransactionAddressRequest request) {
        return transactionSaleQueryService.getTransactionSaleHistoryForApartment(request).stream()
                .map(TransactionSaleApartmentResponse::from)
                .collect(Collectors.toList());
    }

    public List<TransactionSaleOfficetelResponse> getTransactionSaleHistoryForOfficetel(TransactionAddressRequest request) {
        return transactionSaleQueryService.getTransactionSaleHistoryForOfficetel(request).stream()
                .map(TransactionSaleOfficetelResponse::from)
                .collect(Collectors.toList());
    }

    public List<TransactionSaleRowhouseResponse> getTransactionSaleHistoryForRowhouse(TransactionAddressRequest request) {
        return transactionSaleQueryService.getTransactionSaleHistoryForRowhouse(request).stream()
                .map(TransactionSaleRowhouseResponse::from)
                .collect(Collectors.toList());
    }
}
