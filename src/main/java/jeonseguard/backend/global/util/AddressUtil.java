package jeonseguard.backend.global.util;

import jeonseguard.backend.global.exception.error.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressUtil {
    private static final String BUNJI_REGEX = "^\\d{1,4}$";

    public static String extractRegionCode(String regionCode) {
        if (regionCode == null || regionCode.length() != 10) {
            throw new BusinessException(ErrorCode.INVALID_REGION_CODE);
        }
        return regionCode.substring(5, 10);
    }

    public static String formatBunji(String bunji) {
        if (bunji == null || !bunji.matches(BUNJI_REGEX)) {
            throw new BusinessException(ErrorCode.INVALID_BUNJI_FORMAT);
        }
        return String.format("%04d", Integer.parseInt(bunji));
    }

    public static String formatDongName(String dongName) {
        if (dongName == null || dongName.isBlank()) {
            throw new BusinessException(ErrorCode.INVALID_DONG_NAME_FORMAT);
        }
        return dongName.substring(0, dongName.length() - 1);
    }
}
