package jeonseguard.backend.global.util;

import java.time.LocalDate;
import java.time.format.*;
import java.time.temporal.ChronoField;

public class DateUtil {
    // 입력 파싱 전용
    private static final DateTimeFormatter FLEXIBLE_FORMATTER = new DateTimeFormatterBuilder()
            .appendValue(ChronoField.YEAR, 4)
            .appendLiteral('.')
            .appendValue(ChronoField.MONTH_OF_YEAR, 1, 2, SignStyle.NOT_NEGATIVE)
            .appendLiteral('.')
            .appendValue(ChronoField.DAY_OF_MONTH, 1, 2, SignStyle.NOT_NEGATIVE)
            .toFormatter();

    // 출력 포맷 전용
    private static final DateTimeFormatter FIXED_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    public static LocalDate parseDate(String raw) {
        return LocalDate.parse(raw.trim(), FLEXIBLE_FORMATTER);
    }

    public static String formatDate(LocalDate date) {
        return date.format(FIXED_FORMATTER);
    }
}
