package jeonseguard.backend.global.util;

import lombok.*;
import org.springframework.util.StringUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {
    public static boolean isSameText(String a, String b) {
        return StringUtils.hasText(a) && StringUtils.hasText(b) && a.trim().equals(b.trim());
    }

    public static boolean isOptionalSameText(String requestValue, String targetValue) {
        return !StringUtils.hasText(requestValue) || isSameText(requestValue, targetValue);
    }

    public static Optional<String> encodeIfNotBlank(String value) {
        return Optional.ofNullable(value)
                .filter(StringUtils::hasText)
                .map(StringUtil::encode);
    }

    public static String encode(String value) {
        return value != null ? URLEncoder.encode(value, StandardCharsets.UTF_8) : null;
    }
}
