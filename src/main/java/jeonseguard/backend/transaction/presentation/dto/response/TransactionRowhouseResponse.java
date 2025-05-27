package jeonseguard.backend.transaction.presentation.dto.response;

import java.util.List;

public record TransactionRowhouseResponse(
        List<TransactionJeonseRowhouseResponse> jeonseTransactions,
        List<TransactionSaleRowhouseResponse> saleTransactions
) {
    public static TransactionRowhouseResponse of(
            List<TransactionJeonseRowhouseResponse> jeonseTransactions,
            List<TransactionSaleRowhouseResponse> saleTransactions
    ) {
        return new TransactionRowhouseResponse(jeonseTransactions, saleTransactions);
    }
}
