package jeonseguard.backend.global.util;

import lombok.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static jeonseguard.backend.global.util.LongUtil.parsePrice;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionPriceUtil {
    public static <T> List<T> filterByHighestPricePerMonth(
            List<T> transactions,
            Function<T, String> contractYearMonthExtractor,
            Function<T, String> priceExtractor
    ) {
        return transactions.stream()
                .collect(Collectors.toMap(
                        contractYearMonthExtractor,
                        Function.identity(),
                        (existing, replacement) -> selectHigherPrice(existing, replacement, priceExtractor)

                ))
                .values()
                .stream()
                .toList();
    }

    private static <T> T selectHigherPrice(
            T existing,
            T replacement,
            Function<T, String> priceExtractor
    ) {
        return parsePrice(priceExtractor.apply(existing)) >= parsePrice(priceExtractor.apply(replacement)) ? existing : replacement;
    }
}
