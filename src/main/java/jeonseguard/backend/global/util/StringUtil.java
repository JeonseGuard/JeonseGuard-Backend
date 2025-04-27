package jeonseguard.backend.global.util;

import lombok.*;
import org.springframework.util.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {
    public static boolean isSameText(String a, String b) {
        return StringUtils.hasText(a) && StringUtils.hasText(b) && a.trim().equals(b.trim());
    }

    public static boolean isOptionalSameText(String requestValue, String targetValue) {
        return !StringUtils.hasText(requestValue) || isSameText(requestValue, targetValue);
    }
}
