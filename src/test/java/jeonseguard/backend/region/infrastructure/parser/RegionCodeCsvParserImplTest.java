package jeonseguard.backend.region.infrastructure.parser;

import jeonseguard.backend.global.exception.error.BusinessException;
import jeonseguard.backend.global.exception.error.ErrorCode;
import jeonseguard.backend.region.domain.entity.RegionCode;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("RegionCodeCsvParserImpl Test")
class RegionCodeCsvParserImplTest {
    private final RegionCodeCsvParserImpl parser = new RegionCodeCsvParserImpl();

    private static final String VALID_CSV = String.join("\n",
            "법정동코드,시도명,시군구명,읍면동명,리명,순위,생성일자,삭제일자,과거법정동코드",
            "1111010100,서울특별시,종로구,청운효자동,,1,1999-01-10,,1111010000"
    );

    private static final String INVALID_CSV = String.join("\n",
            "시군구명,읍면동명,리명,순위,생성일자,삭제일자,과거법정동코드",
            "종로구,청운효자동,,1,1999-01-10,,1111010000"
    );

    @Test
    @DisplayName("정상적인 CSV 입력 스트림이 주어졌을 때, RegionCode 리스트를 반환한다.")
    void parseRegionCodeTest() {
        // given
        InputStream input = new ByteArrayInputStream(VALID_CSV.getBytes(Charset.forName("EUC-KR")));

        // when
        List<RegionCode> regionCodes = parser.parse(input);

        // then
        assertThat(regionCodes).hasSize(1);
        RegionCode regionCode = regionCodes.get(0);
        assertThat(regionCode.getCode()).isEqualTo("1111010100");
        assertThat(regionCode.getSidoName()).isEqualTo("서울특별시");
        assertThat(regionCode.getSigunguName()).isEqualTo("종로구");
        assertThat(regionCode.getEupmyeondongName()).isEqualTo("청운효자동");
        assertThat(regionCode.getRiName()).isEmpty();
        assertThat(regionCode.getOrderNo()).isEqualTo(1);
        assertThat(regionCode.getCreatedDate()).isEqualTo(LocalDate.of(1999, 1, 10));
        assertThat(regionCode.getDeletedDate()).isNull();
        assertThat(regionCode.getPastCode()).isEqualTo("1111010000");
    }

    @Test
    @DisplayName("CSV에 필수 헤더(법정동코드 등)가 존재하지 않으면, BusinessException이 발생한다.")
    void parseRegionCodeFailTest() {
        // given
        InputStream input = new ByteArrayInputStream(INVALID_CSV.getBytes(Charset.forName("EUC-KR")));

        // when
        BusinessException ex = assertThrows(BusinessException.class, () -> parser.parse(input));

        // then
        assertThat(ex.getErrorCode()).isEqualTo(ErrorCode.CSV_PARSE_ERROR);
    }
}