package jeonseguard.backend.region.application.facade;

import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.region.application.RegionQueryService;
import jeonseguard.backend.region.presentation.dto.DeleteRegionRequest;
import jeonseguard.backend.user.domain.entity.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(SpringExtension.class)
@DisplayName("RegionFacade 단위 테스트")
class RegionFacadeTest {

    @Mock
    private RegionQueryService regionQueryService;

    @Mock
    private UserService userService;

    @InjectMocks
    private RegionFacade regionFacade;

    private User admin;

    private User user;

    @BeforeEach
    void setUp() {
        admin = User.builder()
                .kakaoId(1L)
                .nickname("관리자1")
                .email("admin1@kakao.com")
                .role(Role.ROLE_ADMIN)
                .build();

        user = User.builder()
                .kakaoId(2L)
                .nickname("사용자1")
                .email("user1@kakao.com")
                .role(Role.ROLE_USER)
                .build();
    }

    @Nested
    @DisplayName("deleteRegion 메서드는")
    class DeleteRegion {

        @Test
        @DisplayName("일반 사용자는 행정구역을 삭제할 수 없다.")
        void deleteRegionFailTest() {
            // given
            Long userId = 2L;
            String address = "서울특별시 종로구 청운동";
            DeleteRegionRequest request = new DeleteRegionRequest(address);
            given(userService.getUser(userId)).willReturn(user);

            // when & then
            BusinessException ex = assertThrows(BusinessException.class, () -> regionFacade.deleteRegion(userId, request));
            assertThat(ex.getErrorCode()).isEqualTo(ErrorCode.REGION_DELETE_FORBIDDEN);
        }

        @Test
        @DisplayName("관리자 권한을 가진 사용자는 행정구역을 삭제할 수 있다.")
        void DeleteRegionTest() {
            // given
            Long adminId = 1L;
            String address = "서울특별시 종로구 청운동";
            DeleteRegionRequest request = new DeleteRegionRequest(address);
            given(userService.getUser(adminId)).willReturn(admin);

            // when& then
            assertThatNoException().isThrownBy(() -> regionFacade.deleteRegion(adminId, request));
            then(regionQueryService).should().deleteRegion(request);
        }
    }
}