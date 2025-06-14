package jeonseguard.backend.transaction.presentation.dto.response;

import java.util.List;

public record TransactionSummaryApartmentResponse(
        List<TransactionJeonseApartmentResponse> jeonses,
        List<TransactionSaleApartmentResponse> sales
) {
    public static TransactionSummaryApartmentResponse of(
            List<TransactionJeonseApartmentResponse> jeonses,
            List<TransactionSaleApartmentResponse> sales
    ) {
        return new TransactionSummaryApartmentResponse(jeonses, sales);
    }
}
