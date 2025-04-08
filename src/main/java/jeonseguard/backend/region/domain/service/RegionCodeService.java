package jeonseguard.backend.region.domain.service;

import jeonseguard.backend.global.config.properties.RegionCodeProperties;
import jeonseguard.backend.region.domain.entity.RegionCode;
import jeonseguard.backend.region.domain.parser.RegionCodeCsvParser;
import jeonseguard.backend.region.domain.repository.RegionCodeRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionCodeService {
    private final RegionCodeRepository regionCodeRepository;
    private final RegionCodeCsvParser regionCodeCsvParser;
    private final RegionCodeProperties regionCodeProperties;

    @Transactional
    public void saveNewRegionCodes(InputStream inputStream) {
        if (regionCodeRepository.existsByCode(regionCodeProperties.finalCode())) {
            return;
        }
        regionCodeRepository.saveAll(filterNewRegionCodes(inputStream));
    }

    private List<RegionCode> filterNewRegionCodes(InputStream inputStream) {
        return regionCodeCsvParser.parse(inputStream).stream()
                .filter(code -> !regionCodeRepository.existsByCode(code.getCode()))
                .toList();
    }
}
