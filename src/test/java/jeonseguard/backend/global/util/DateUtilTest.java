package jeonseguard.backend.global.util;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("DateUtil Test")
class DateUtilTest {

    @Nested
    @DisplayName("parseDate 메서드는")
    class parsedDate {

        @Test
        @DisplayName("yyyy.M.d 형식 문자열을 LocalDate로 변환한다.")
        void parseLocalDateTest() {
            // given
            String rawDate = "1999-01-01";

            // when
            LocalDate parsedDate = DateUtil.parseDate(rawDate);

            // then
            assertThat(parsedDate).isEqualTo(LocalDate.of(1999, 1, 1));
        }

        @Test
        @DisplayName("앞뒤 공백이 포함된 날짜 문자열 입력 시, LocalDate로 정상 변환한다.")
        void parseLocalDateWithSpacesTest() {
            // given
            String rawDate = " 2025-12-10 ";

            // when
            LocalDate parsedDate = DateUtil.parseDate(rawDate);

            // then
            assertThat(parsedDate).isEqualTo(LocalDate.of(2025, 12, 10));
        }

        @Test
        @DisplayName("잘못된 형식의 문자열이 들어오면 예외가 발생한다.")
        void parseLocalDateFailTest() {
            // given
            String rawDate = "2025/12/10";

            // when & then
            assertThrows(DateTimeParseException.class, () -> DateUtil.parseDate(rawDate));
        }
    }

    @Nested
    @DisplayName("formatDate 메서드는")
    class formatDate {

        @Test
        @DisplayName("LocalDate가 주어졌을 때, yyyy.MM.dd 형식 문자열로 변환한다.")
        void formatLocalDateTest() {
            // given
            LocalDate date = LocalDate.of(2025, 5, 5);

            // when
            String rawDate = DateUtil.formatDate(date);

            // then
            assertThat(rawDate).isEqualTo("2025.05.05");
        }
    }
}