package jeonseguard.backend.region.domain.parser;

import jeonseguard.backend.region.domain.entity.RegionCode;

import java.io.InputStream;
import java.util.List;

public interface RegionCodeCsvParser {
    List<RegionCode> parse(InputStream input);
}
