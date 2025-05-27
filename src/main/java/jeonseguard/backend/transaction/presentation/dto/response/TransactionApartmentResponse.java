package jeonseguard.backend.transaction.presentation.dto.response;

import java.util.List;

public record TransactionApartmentResponse(
        List<TransactionJeonseApartmentResponse> jeonseTransactions,
        List<TransactionSaleApartmentResponse> saleTransactions
) {
    public static TransactionApartmentResponse of(
            List<TransactionJeonseApartmentResponse> jeonseTransactions,
            List<TransactionSaleApartmentResponse> saleTransactions
    ) {
        return new TransactionApartmentResponse(jeonseTransactions, saleTransactions);
    }
}
