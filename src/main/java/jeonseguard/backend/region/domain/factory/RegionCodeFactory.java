package jeonseguard.backend.region.domain.factory;

import jeonseguard.backend.region.domain.entity.RegionCode;
import org.apache.commons.csv.CSVRecord;

import java.time.LocalDate;

public class RegionCodeFactory {
    public static RegionCode fromRecord(CSVRecord record, Integer orderNo, LocalDate createdDate, LocalDate deletedDate) {
        return RegionCode.builder()
                .code(record.get("법정동코드"))
                .sidoName(record.get("시도명"))
                .sigunguName(record.get("시군구명"))
                .eupmyeondongName(record.get("읍면동명"))
                .riName(record.get("리명"))
                .orderNo(orderNo)
                .createdDate(createdDate)
                .deletedDate(deletedDate)
                .pastCode(record.get("과거법정동코드"))
                .build();
    }
}
