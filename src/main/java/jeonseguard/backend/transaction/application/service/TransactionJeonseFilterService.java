package jeonseguard.backend.transaction.application.service;

import jeonseguard.backend.transaction.domain.entity.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static jeonseguard.backend.global.util.LongUtil.parsePrice;

@Service
public class TransactionJeonseFilterService {
    public List<TransactionJeonseApartment> filterHighestPricePerContractYearMonthForJeonseApartment(List<TransactionJeonseApartment> transactions) {
        Map<String, TransactionJeonseApartment> highestPriceByMonth = extractHighestPriceByMonth(
                transactions,
                TransactionJeonseApartment::getContractYearMonth,
                TransactionJeonseApartment::getPrice
        );
        return List.copyOf(highestPriceByMonth.values());
    }

    public List<TransactionJeonseOfficetel> filterHighestPricePerContractYearMonthForJeonseOfficetel(List<TransactionJeonseOfficetel> transactions) {
        Map<String, TransactionJeonseOfficetel> highestPriceByMonth = extractHighestPriceByMonth(
                transactions,
                TransactionJeonseOfficetel::getContractYearMonth,
                TransactionJeonseOfficetel::getPrice
        );
        return List.copyOf(highestPriceByMonth.values());
    }

    public List<TransactionJeonseRowhouse> filterHighestPricePerContractYearMonthForJeonseRowhouse(List<TransactionJeonseRowhouse> transactions) {
        Map<String, TransactionJeonseRowhouse> highestPriceByMonth = extractHighestPriceByMonth(
                transactions,
                TransactionJeonseRowhouse::getContractYearMonth,
                TransactionJeonseRowhouse::getPrice
        );
        return List.copyOf(highestPriceByMonth.values());
    }

    private <T> Map<String, T> extractHighestPriceByMonth(List<T> transactions, Function<T, String> contractYearMonthExtractor, Function<T, String> priceExtractor) {
        return transactions.stream()
                .collect(Collectors.toMap(
                        contractYearMonthExtractor,
                        Function.identity(),
                        (existing, replacement) -> comparePriceDescending(existing, replacement, priceExtractor))
                );
    }

    private <T> T comparePriceDescending(T existing, T replacement, Function<T, String> priceExtractor) {
        long existingPrice = parsePrice(priceExtractor.apply(existing));
        long replacementPrice = parsePrice(priceExtractor.apply(replacement));
        return replacementPrice > existingPrice ? replacement : existing;
    }
}
