package jeonseguard.backend.global.util;

import java.time.LocalDate;
import java.time.format.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {
    private static final DateTimeFormatter FLEXIBLE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FIXED_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    public static LocalDate parseDate(String raw) {
        return LocalDate.parse(raw.trim(), FLEXIBLE_FORMATTER);
    }

    public static String formatDate(LocalDate date) {
        return date.format(FIXED_FORMATTER);
    }
}
