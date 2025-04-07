package jeonseguard.backend.region.infrastructure.parser;

import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.global.util.DateUtil;
import jeonseguard.backend.region.domain.entity.RegionCode;
import jeonseguard.backend.region.domain.factory.RegionCodeFactory;
import jeonseguard.backend.region.domain.parser.RegionCodeCsvParser;
import org.apache.commons.csv.*;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

@Component
public class RegionCodeCsvParserImpl implements RegionCodeCsvParser {

    @Override
    public List<RegionCode> parse(InputStream input) {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
                CSVParser csvParser = CSVParser.parse(reader, createCsvFormat())
        ) {
            List<RegionCode> regionCodes = new ArrayList<>();
            for (CSVRecord record : csvParser) {
                Integer orderNo = parseNullableInteger(record.get("순위"));
                LocalDate createdDate = parseNullableLocalDate(record.get("생성일자"));
                LocalDate deletedDate = parseNullableLocalDate(record.get("삭제일자"));
                regionCodes.add(RegionCodeFactory.fromRecord(record, orderNo, createdDate, deletedDate));
            }
            return regionCodes;
        } catch (IOException ex) {
            throw new BusinessException(ErrorCode.CSV_PARSE_ERROR);
        }
    }

    private Integer parseNullableInteger(String raw) {
        return raw.isBlank() ? null : Integer.valueOf(raw.trim());
    }

    private LocalDate parseNullableLocalDate(String raw) {
        return raw.isBlank() ? null : DateUtil.parseDate(raw.trim());
    }

    private CSVFormat createCsvFormat() {
        return CSVFormat.DEFAULT.builder()
                .setHeader()
                .setIgnoreHeaderCase(true)
                .setTrim(true)
                .build();
    }
}
