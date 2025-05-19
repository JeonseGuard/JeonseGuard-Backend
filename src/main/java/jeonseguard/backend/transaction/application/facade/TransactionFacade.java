package jeonseguard.backend.transaction.application.facade;

import jeonseguard.backend.transaction.application.service.TransactionJeonseService;
import jeonseguard.backend.transaction.presentation.dto.request.TransactionJeonseAddressRequest;
import jeonseguard.backend.transaction.presentation.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionFacade {
    private final TransactionJeonseService jeonseService;

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
}
