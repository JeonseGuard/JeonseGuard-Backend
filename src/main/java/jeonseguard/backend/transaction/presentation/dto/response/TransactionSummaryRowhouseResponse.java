package jeonseguard.backend.transaction.presentation.dto.response;

import java.util.List;

public record TransactionSummaryRowhouseResponse(
        List<TransactionJeonseRowhouseResponse> jeonses,
        List<TransactionSaleRowhouseResponse> sales
) {
    public static TransactionSummaryRowhouseResponse of(
            List<TransactionJeonseRowhouseResponse> jeonses,
            List<TransactionSaleRowhouseResponse> sales
    ) {
        return new TransactionSummaryRowhouseResponse(jeonses, sales);
    }
}
