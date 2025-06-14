package jeonseguard.backend.region.infrastructure;

import jeonseguard.backend.region.domain.entity.Region;
import jeonseguard.backend.region.infrastructure.repository.RegionJpaRepository;
import jeonseguard.backend.region.infrastructure.repository.RegionRepositoryImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("RegionRepositoryImpl 단위 테스트")
class RegionRepositoryImplTest {

    @InjectMocks
    private RegionRepositoryImpl regionRepository;

    @Mock
    private RegionJpaRepository jpaRepository;

    private Region region;

    @BeforeEach
    void setup() {
        region = Region.builder()
                .regionCode("1111010100")
                .sigunguCode("11110")
                .address("서울특별시 종로구 청운동")
                .build();
    }

    @Nested
    @DisplayName("findByAddress 메서드는")
    class FindByAddress {

        @Test
        @DisplayName("존재하지 않는 주소가 들어오면 Optional.empty()를 반환한다.")
        void findByAddressFailTest() {
            // given
            String unknownAddress = "서울특별시 종로구 천사동";
            given(jpaRepository.findByAddress(unknownAddress)).willReturn(Optional.empty());

            // when
            Optional<Region> result = regionRepository.findByAddress(unknownAddress);

            // then
            assertThat(result).isNotPresent();
            then(jpaRepository).should().findByAddress(unknownAddress);
        }

        @Test
        @DisplayName("올바른 주소로 행정구역을 조회한다.")
        void findByAddressTest() {
            // given
            String address = "서울특별시 종로구 청운동";
            given(jpaRepository.findByAddress(address)).willReturn(Optional.of(region));

            // when
            Optional<Region> result = regionRepository.findByAddress(address);

            // then
            assertThat(result).isPresent();
            assertThat(result.get().getRegionCode()).isEqualTo(region.getRegionCode());
            assertThat(result.get().getSigunguCode()).isEqualTo(region.getSigunguCode());
            assertThat(result.get().getAddress()).isEqualTo(region.getAddress());
        }
    }

    @Nested
    @DisplayName("deleteByAddress 메서드는")
    class DeleteByAddress {

        @Test
        @DisplayName("존재하지 않는 주소라도 예외 없이 호출된다.")
        void deleteByAddressFailTest() {
            // given
            String unknownAddress = "서울특별시 종로구 악마동";
            willDoNothing().given(jpaRepository).deleteByAddress(unknownAddress);

            // when
            regionRepository.deleteByAddress(unknownAddress);

            // then
            then(jpaRepository).should().deleteByAddress(unknownAddress);
        }

        @Test
        @DisplayName("올바른 주소로 행정구역을 삭제한다.")
        void deleteByAddressTest() {
            // given
            String address = "서울특별시 종로구 청운동";
            willDoNothing().given(jpaRepository).deleteByAddress(address);

            // when
            regionRepository.deleteByAddress(address);

            // then
            then(jpaRepository).should().deleteByAddress(address);
        }
    }
}