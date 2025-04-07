package jeonseguard.backend.global.util;

import java.time.LocalDate;
import java.time.format.*;
import java.time.temporal.ChronoField;

public class DateUtil {
    private static final DateTimeFormatter FLEXIBLE_FORMATTER = new DateTimeFormatterBuilder()
            .appendValue(ChronoField.YEAR, 4)
            .appendLiteral('.')
            .appendValue(ChronoField.MONTH_OF_YEAR, 1, 2, java.time.format.SignStyle.NOT_NEGATIVE)
            .appendLiteral('.')
            .appendValue(ChronoField.DAY_OF_MONTH, 1, 2, java.time.format.SignStyle.NOT_NEGATIVE)
            .toFormatter();

    public static LocalDate parseDate(String raw) {
        return LocalDate.parse(raw.trim(), FLEXIBLE_FORMATTER);
    }
}
