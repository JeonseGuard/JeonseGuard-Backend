package jeonseguard.backend.global.util;

import lombok.*;
import org.springframework.util.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LongUtil {
    public static Long parsePrice(String amount) {
        if (!StringUtils.hasText(amount)) {
            return 0L;
        } else {
            return Long.parseLong(amount.replaceAll("[^0-9]", ""));
        }
    }
}
