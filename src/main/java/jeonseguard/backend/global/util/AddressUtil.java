package jeonseguard.backend.global.util;

import jeonseguard.backend.global.exception.error.*;

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
}
