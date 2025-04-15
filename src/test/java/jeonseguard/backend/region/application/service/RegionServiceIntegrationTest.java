package jeonseguard.backend.region.application.service;

import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.region.domain.entity.Region;
import jeonseguard.backend.region.domain.repository.*;
import jeonseguard.backend.region.presentation.dto.DeleteRegionRequest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@DisplayName("RegionService 통합 테스트")
class RegionServiceIntegrationTest {

    @Autowired
    private RegionService regionService;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private RegionStore regionStore;

    @AfterEach
    void tearDown() {
        Region region = Region.builder()
                .regionCode("1111010100")
                .sigunguCode("11110")
                .address("서울특별시 종로구 청운동")
                .build();

        regionRepository.save(region);
    }

    @Nested
    @DisplayName("getRegionCode 메서드는")
    class getRegionCode {

        @Test
        @DisplayName("존재하지 않는 주소가 들어오면 예외가 발생한다.")
        void getRegionCodeFailTest() {
            // given
            String unknownAddress = "서울특별시 종로구 천사동";

            // when
            BusinessException ex = Assertions.assertThrows(BusinessException.class, () -> regionService.getRegionCode(unknownAddress));

            // then
            assertThat(ex.getErrorCode()).isEqualTo(ErrorCode.REGION_NOT_FOUND);
        }

        @Test
        @DisplayName("DB에 존재하는 주소이면, regionCode를 반환하고 캐시한다")
        void getRegionCodeTest() {
            // given
            String address = "서울특별시 종로구 신교동";
            String expectedRegionCode = "1111010200";

            // when
            String result = regionService.getRegionCode(address);
            String cached = regionStore.findRegionCodeByAddress(address).orElse(null);

            // then
            assertThat(result).isEqualTo(expectedRegionCode);
            assertThat(cached).isEqualTo(expectedRegionCode);
        }
    }

    @Nested
    @DisplayName("getSigunguCode 메서드는")
    class getSigunguCode {

        @Test
        @DisplayName("존재하지 않는 주소가 들어오면 예외가 발생한다.")
        void getSigunguCodeFailTest() {
            // given
            String unknownAddress = "서울특별시 종로구 악마동";

            // when
            BusinessException ex = Assertions.assertThrows(BusinessException.class, () -> regionService.getRegionCode(unknownAddress));

            // then
            assertThat(ex.getErrorCode()).isEqualTo(ErrorCode.REGION_NOT_FOUND);
        }

        @Test
        @DisplayName("DB에 존재하는 주소이면, sigunguCode를 반환하고 캐시한다")
        void getSigunguCodeTest() {
            // given
            String address = "서울특별시 종로구 신교동";
            String expectedSigunguCode = "11110";

            // when
            String result = regionService.getSigunguCode(address);
            String cached = regionStore.findSigunguCodeByAddress(address).orElse(null);

            // then
            assertThat(result).isEqualTo(expectedSigunguCode);
            assertThat(cached).isEqualTo(expectedSigunguCode);
        }
    }

    @Nested
    @DisplayName("deleteRegion 메서드는")
    class deleteRegionByAddress {

        @Test
        @DisplayName("올바른 주소가 들어오면, 행정구역 정보를 DB와 캐시 모두에서 삭제한다.")
        void deleteRegionTest() {
            // given
            String address = "서울특별시 종로구 청운동";
            DeleteRegionRequest request = new DeleteRegionRequest(address);

            // when
            regionService.deleteRegion(request);

            // then
            assertThat(regionStore.findRegionCodeByAddress(address)).isEmpty();
            assertThat(regionStore.findSigunguCodeByAddress(address)).isEmpty();
            assertThat(regionRepository.findByAddress(address)).isEmpty();
        }
    }
}
