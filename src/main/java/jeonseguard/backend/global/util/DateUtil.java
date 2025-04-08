package jeonseguard.backend.global.util;

import java.time.LocalDate;
import java.time.format.*;

public class DateUtil {
    // 입력 파싱 전용
    private static final DateTimeFormatter FLEXIBLE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // 출력 포맷 전용
    private static final DateTimeFormatter FIXED_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    public static LocalDate parseDate(String raw) {
        return LocalDate.parse(raw.trim(), FLEXIBLE_FORMATTER);
    }

    public static String formatDate(LocalDate date) {
        return date.format(FIXED_FORMATTER);
    }
}
