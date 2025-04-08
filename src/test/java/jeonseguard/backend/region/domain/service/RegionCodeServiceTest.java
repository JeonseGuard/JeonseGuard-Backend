package jeonseguard.backend.region.domain.service;

import jeonseguard.backend.global.exception.error.*;
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

    @InjectMocks
    RegionCodeService regionCodeService;

    @Test
    @DisplayName("CSV에서 파싱된 RegionCode 중 기존에 존재하지 않는 코드만 저장한다.")
    void saveNewRegionCodesTest() {
        // given
        InputStream inputStream = mock(InputStream.class);
        RegionCode newCode = RegionCode.builder()
                .code("1111010100")
                .sidoName("서울특별시")
                .createdDate(LocalDate.of(1999, 1, 1))
                .build();
        RegionCode existingCode = RegionCode.builder()
                .code("9999999999")
                .sidoName("기존법정동")
                .createdDate(LocalDate.of(1990, 1, 1))
                .build();
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
    @DisplayName("CsvParser에서 예외가 발생하면, BusinessException이 발생한다.")
    void saveNewRegionCodesFailTest() {
        // given
        InputStream inputStream = mock(InputStream.class);
        given(regionCodeCsvParser.parse(inputStream)).willThrow(new BusinessException(ErrorCode.CSV_PARSE_ERROR));

        // when & then
        BusinessException ex = assertThrows(BusinessException.class, () -> regionCodeService.saveNewRegionCodes(inputStream));
        assertThat(ex.getErrorCode()).isEqualTo(ErrorCode.CSV_PARSE_ERROR);
        then(regionCodeRepository).shouldHaveNoInteractions();
    }
}
