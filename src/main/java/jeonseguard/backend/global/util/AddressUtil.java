package jeonseguard.backend.global.util;

import jeonseguard.backend.global.exception.error.*;
import lombok.*;

import static org.springframework.util.StringUtils.hasText;

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
        if (!hasText(dongName)) {
            throw new BusinessException(ErrorCode.INVALID_DONG_NAME_FORMAT);
        }
        return dongName.substring(0, dongName.length() - 1);
    }

    public static String formatFloorName(String floorName) {
        if (!hasText(floorName)) {
            throw new BusinessException(ErrorCode.INVALID_FLOOR_NAME_FORMAT);
        }
        return floorName.substring(0, floorName.length() - 1);
    }

    public static String formatHoName(String hoName) {
        if (!hasText(hoName)) {
            throw new BusinessException(ErrorCode.INVALID_HO_NAME_FORMAT);
        }
        return hoName.substring(0, hoName.length() - 1);
    }
}
