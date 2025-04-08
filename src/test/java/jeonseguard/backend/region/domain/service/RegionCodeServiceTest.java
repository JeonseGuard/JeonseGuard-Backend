package jeonseguard.backend.region.domain.service;

import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.global.config.properties.RegionCodeProperties;
import jeonseguard.backend.region.domain.entity.RegionCode;
import jeonseguard.backend.region.domain.parser.RegionCodeCsvParser;
import jeonseguard.backend.region.domain.repository.RegionCodeRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

@DisplayName("RegionCodeService Test")
@ExtendWith(MockitoExtension.class)
class RegionCodeServiceTest {

    @Mock
    RegionCodeRepository regionCodeRepository;

    @Mock
    RegionCodeCsvParser regionCodeCsvParser;

    @Mock
    RegionCodeProperties regionCodeProperties;

    @InjectMocks
    RegionCodeService regionCodeService;

    @BeforeEach
    void setup() {
        given(regionCodeProperties.finalCode()).willReturn("0000000000");
        given(regionCodeRepository.existsByCode("0000000000")).willReturn(false);
    }

    @Test
    @DisplayName("CSV에서 파싱된 RegionCode 중 기존에 존재하지 않는 코드만 저장한다.")
    void saveNewRegionCodesTest() {
        // given
        InputStream inputStream = mock(InputStream.class);
        RegionCode newCode = createRegionCode("1111010100", "서울특별시", LocalDate.of(1999, 1, 1));
        RegionCode existingCode = createRegionCode("9999999999", "기존법정동", LocalDate.of(1990, 1, 1));
        given(regionCodeCsvParser.parse(inputStream)).willReturn(List.of(newCode, existingCode));
        given(regionCodeRepository.existsByCode("1111010100")).willReturn(false);
        given(regionCodeRepository.existsByCode("9999999999")).willReturn(true);

        // when & then
        assertDoesNotThrow(() -> regionCodeService.saveNewRegionCodes(inputStream));
        then(regionCodeRepository).should(times(1)).saveAll(List.of(newCode));
        then(regionCodeRepository).should(times(1)).existsByCode("1111010100");
        then(regionCodeRepository).should(times(1)).existsByCode("9999999999");
    }

    @Test
    @DisplayName("CSVParser에서 예외가 발생하면, BusinessException이 발생한다.")
    void saveNewRegionCodesFailTest() {
        // given
        InputStream inputStream = mock(InputStream.class);
        given(regionCodeProperties.finalCode()).willReturn("0000000000");
        given(regionCodeRepository.existsByCode("0000000000")).willReturn(false);
        given(regionCodeCsvParser.parse(inputStream)).willThrow(new BusinessException(ErrorCode.CSV_PARSE_ERROR));

        // when & then
        BusinessException ex = assertThrows(BusinessException.class, () -> regionCodeService.saveNewRegionCodes(inputStream));
        assertThat(ex.getErrorCode()).isEqualTo(ErrorCode.CSV_PARSE_ERROR);
        then(regionCodeRepository).should().existsByCode("0000000000");
        then(regionCodeRepository).shouldHaveNoMoreInteractions();
    }

    private RegionCode createRegionCode(String code, String sidoName, LocalDate createdDate) {
        return RegionCode.builder()
                .code(code)
                .sidoName(sidoName)
                .createdDate(createdDate)
                .build();
    }
}
