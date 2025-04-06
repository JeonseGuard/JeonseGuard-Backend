package jeonseguard.backend.region.infrastructure.parser;

import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.region.domain.entity.RegionCode;
import jeonseguard.backend.region.domain.factory.RegionCodeFactory;
import jeonseguard.backend.region.domain.parser.RegionCodeCsvParser;
import org.apache.commons.csv.*;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
                regionCodes.add(RegionCodeFactory.fromRecord(record));
            }
            return regionCodes;
        } catch (IOException ex) {
            throw new BusinessException(ErrorCode.CSV_PARSE_ERROR);
        }
    }

    private CSVFormat createCsvFormat() {
        return CSVFormat.DEFAULT.builder()
                .setHeader()
                .setIgnoreHeaderCase(true)
                .setTrim(true)
                .build();
    }
}
