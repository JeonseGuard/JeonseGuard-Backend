package jeonseguard.backend.global.util;

import jeonseguard.backend.global.exception.error.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

@DisplayName("AddressUtil 단위 테스트")
class AddressUtilTest {

    @Nested
    @DisplayName("extractRegionCode 메서드는")
    class ExtractRegionCode {

        @Test
        @DisplayName("regionCode가 null이면, INVALID_REGION_CODE 예외가 발생한다.")
        @SuppressWarnings("DataFlowIssue")
        void throwExceptionWhenRegionCodeIsNull() {
            // when & then
            assertThatThrownBy(() -> AddressUtil.extractRegionCode(null))
                    .isInstanceOf(BusinessException.class)
                    .hasFieldOrPropertyWithValue("errorCode", ErrorCode.INVALID_REGION_CODE);
        }

        @Test
        @DisplayName("regionCode가 10자리보다 짧으면, INVALID_REGION_CODE 예외가 발생한다.")
        void throwExceptionWhenRegionCodeIsTooShort() {
            // given
            String tooShort = "1234";

            // when & then
            assertThatThrownBy(() -> AddressUtil.extractRegionCode(tooShort))
                    .isInstanceOf(BusinessException.class)
                    .hasFieldOrPropertyWithValue("errorCode", ErrorCode.INVALID_REGION_CODE);
        }

        @Test
        @DisplayName("regionCode가 10자리보다 길면, INVALID_REGION_CODE 예외가 발생한다.")
        void throwExceptionWhenRegionCodeIsTooLong() {
            // given
            String tooLong = "12345678901234567890";

            // when & then
            assertThatThrownBy(() -> AddressUtil.extractRegionCode(tooLong))
                    .isInstanceOf(BusinessException.class)
                    .hasFieldOrPropertyWithValue("errorCode", ErrorCode.INVALID_REGION_CODE);
        }

        @Test
        @DisplayName("10자리 regionCode에서 뒤 5자리를 잘라 반환한다")
        void extractRegionCodeSuccessTest() {
            // given
            String regionCode = "1141010400";

            // when
            String result = AddressUtil.extractRegionCode(regionCode);

            // then
            assertThat(result).isEqualTo("10400");
        }
    }

    @Nested
    @DisplayName("formatBunji 메서드는")
    class FormatBunji {

        @Test
        @DisplayName("입력이 null이면, INVALID_BUNJI_FORMAT 예외가 발생한다.")
        @SuppressWarnings("DataFlowIssue")
        void throwExceptionWhenBunjiIsNull() {
            // when & then
            assertThatThrownBy(() -> AddressUtil.formatBunji(null))
                    .isInstanceOf(BusinessException.class)
                    .hasFieldOrPropertyWithValue("errorCode", ErrorCode.INVALID_BUNJI_FORMAT);
        }

        @Test
        @DisplayName("5자리 이상 숫자 문자열이 들어오면, INVALID_BUNJI_FORMAT 예외가 발생한다.")
        void throwExceptionWhenBunjiIsTooLong() {
            // given
            String tooLong = "12345";

            // when & then
            assertThatThrownBy(() -> AddressUtil.formatBunji(tooLong))
                    .isInstanceOf(BusinessException.class)
                    .hasFieldOrPropertyWithValue("errorCode", ErrorCode.INVALID_BUNJI_FORMAT);
        }

        @Test
        @DisplayName("숫자가 아닌 문자열이 들어오면, INVALID_BUNJI_FORMAT 예외가 발생한다.")
        void throwExceptionWhenBunjiIsNotNumeric() {
            // given
            String invalidFormatString = "abcd";

            // when & then
            assertThatThrownBy(() -> AddressUtil.formatBunji(invalidFormatString))
                    .isInstanceOf(BusinessException.class)
                    .hasFieldOrPropertyWithValue("errorCode", ErrorCode.INVALID_BUNJI_FORMAT);
        }

        @Test
        @DisplayName("1~4자리 숫자 문자열을 변환하여, 4자리 숫자 문자열로 반환한다.")
        void formatBunjiSuccessTest() {
            // given
            String one = "1";
            String two = "96";
            String four = "1234";

            // when & then
            assertThat(AddressUtil.formatBunji(one)).isEqualTo("0001");
            assertThat(AddressUtil.formatBunji(two)).isEqualTo("0096");
            assertThat(AddressUtil.formatBunji(four)).isEqualTo("1234");
        }
    }
}
