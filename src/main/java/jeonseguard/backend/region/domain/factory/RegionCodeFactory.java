package jeonseguard.backend.region.domain.factory;

import jeonseguard.backend.global.util.DateUtil;
import jeonseguard.backend.region.domain.entity.RegionCode;
import org.apache.commons.csv.CSVRecord;

import java.time.LocalDate;

public class RegionCodeFactory {
    public static RegionCode fromRecord(CSVRecord record) {
        return RegionCode.builder()
                .code(record.get("법정동코드"))
                .sidoName(record.get("시도명"))
                .sigunguName(record.get("시군구명"))
                .eupmyeondongName(record.get("읍면동명"))
                .riName(record.get("리명"))
                .orderNo(parseNullableInteger(record.get("순위")))
                .createdDate(parseNullableLocalDate(record.get("생성일자")))
                .deletedDate(parseNullableLocalDate(record.get("삭제일자")))
                .pastCode(record.get("과거법정동코드"))
                .build();
    }

    private static Integer parseNullableInteger(String raw) {
        return raw.isBlank() ? null : Integer.valueOf(raw.trim());
    }

    private static LocalDate parseNullableLocalDate(String raw) {
        return raw.isBlank() ? null : DateUtil.parseDate(raw.trim());
    }
}
