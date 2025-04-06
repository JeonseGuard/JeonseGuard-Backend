package jeonseguard.backend.region.domain.factory;

import jeonseguard.backend.region.domain.entity.RegionCode;
import org.apache.commons.csv.CSVRecord;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegionCodeFactory {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    public static RegionCode fromRecord(CSVRecord record) {
        return RegionCode.builder()
                .code(record.get("법정동코드"))
                .sidoName(record.get("시도명"))
                .sigunguName(record.get("시군구명"))
                .eupmyeondongName(record.get("읍면동명"))
                .riName(record.get("리명"))
                .orderNo(record.get("순위").isBlank() ? null : Integer.valueOf(record.get("순위")))
                .createdDate(parseDate(record.get("생성일자")))
                .deletedDate(record.get("삭제일자").isBlank() ? null : parseDate(record.get("삭제일자")))
                .pastCode(record.get("과거법정동코드"))
                .build();
    }

    private static LocalDate parseDate(String raw) {
        return LocalDate.parse(raw.trim(), FORMATTER);
    }
}
