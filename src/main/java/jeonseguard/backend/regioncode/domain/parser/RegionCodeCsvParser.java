package jeonseguard.backend.regioncode.domain.parser;

import jeonseguard.backend.regioncode.domain.entity.RegionCode;

import java.io.InputStream;
import java.util.List;

public interface RegionCodeCsvParser {
    List<RegionCode> parse(InputStream input);
}
