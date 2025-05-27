package jeonseguard.backend.transaction.presentation.dto.response;

import java.util.List;

public record TransactionOfficetelResponse(
        List<TransactionJeonseOfficetelResponse> jeonseTransactions,
        List<TransactionSaleOfficetelResponse> saleTransactions
) {
    public static TransactionOfficetelResponse of(
            List<TransactionJeonseOfficetelResponse> jeonseTransactions,
            List<TransactionSaleOfficetelResponse> saleTransactions
    ) {
        return new TransactionOfficetelResponse(jeonseTransactions, saleTransactions);
    }
}
