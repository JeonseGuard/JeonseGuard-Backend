package jeonseguard.backend.building.infrastructure.repository;

import jeonseguard.backend.building.infrastructure.dto.external.*;
import jeonseguard.backend.building.infrastructure.dto.request.BuildingRegisterRequest;
import jeonseguard.backend.global.config.properties.BuildingRegisterProperties;
import jeonseguard.backend.global.dto.OpenApiResponse;
import jeonseguard.backend.global.exception.error.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("BuildingRegisterAreaApiRepositoryImpl 단위 테스트")
@SuppressWarnings({"rawtypes", "unchecked"})
class BuildingRegisterAreaApiRepositoryImplTest {

    @Mock private WebClient webClient;
    @Mock private WebClient.RequestHeadersUriSpec uriSpec;
    @Mock private WebClient.RequestHeadersSpec headersSpec;
    @Mock private WebClient.ResponseSpec responseSpec;
    @Mock private BuildingRegisterProperties buildingRegisterProperties;
    @InjectMocks private BuildingRegisterAreaApiRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        lenient().when(buildingRegisterProperties.overviewUri()).thenReturn("https://example.com/overview");
        lenient().when(buildingRegisterProperties.floorUri()).thenReturn("https://example.com/floor");
        lenient().when(buildingRegisterProperties.areaUri()).thenReturn("https://example.com/area");
        lenient().when(buildingRegisterProperties.serviceKey()).thenReturn("test-key");
        lenient().when(buildingRegisterProperties.categoryCode()).thenReturn("0");
        lenient().when(buildingRegisterProperties.listSize()).thenReturn("10");
    }

    private <T> void mockWebClientResponse(List<T> items) {
        OpenApiResponse<T> apiResponse = new OpenApiResponse<>(
                new OpenApiResponse.Response<>(
                        new OpenApiResponse.Body<>(
                                new OpenApiResponse.Items<>(items)
                        )
                )
        );
        when(webClient.get()).thenReturn(uriSpec);
        when(uriSpec.uri(any(URI.class))).thenReturn(headersSpec);
        when(headersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(any(ParameterizedTypeReference.class))).thenReturn(Mono.just(apiResponse));
    }

    private void mockWebClientEmpty() {
        when(webClient.get()).thenReturn(uriSpec);
        when(uriSpec.uri(any(URI.class))).thenReturn(headersSpec);
        when(headersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(any(ParameterizedTypeReference.class))).thenReturn(Mono.empty());
    }

    @Nested
    @DisplayName("fetchBuildingRegisterOverview 메서드는")
    class FetchBuildingRegisterOverview {

        @Test
        @DisplayName("응답이 없으면 BUILDING_REGISTER_OVERVIEW_FETCH_ERROR 예외를 던진다")
        void throwExceptionWhenResponseIsNull() {
            // given
            BuildingRegisterRequest request = mock(BuildingRegisterRequest.class);
            mockWebClientEmpty();

            // when & then
            assertThatThrownBy(() -> repository.fetchBuildingRegisterOverview("1", request))
                    .isInstanceOf(BusinessException.class)
                    .hasFieldOrPropertyWithValue("errorCode", ErrorCode.BUILDING_REGISTER_OVERVIEW_FETCH_ERROR);
        }

        @Test
        @DisplayName("정상적으로 리스트를 반환한다")
        void fetchBuildingRegisterOverviewSuccess() {
            // given
            BuildingRegisterRequest request = mock(BuildingRegisterRequest.class);
            BuildingRegisterOverviewItem item = new BuildingRegisterOverviewItem("별관동", "서울특별시 종로구 천사로100길 56-1", "100.0", "80.0", "철근콘크리트", "공동주택");
            mockWebClientResponse(List.of(item));

            // when
            List<BuildingRegisterOverviewItem> result = repository.fetchBuildingRegisterOverview("1", request);

            // then
            assertThat(result)
                    .isNotEmpty()
                    .hasSize(1)
                    .extracting(BuildingRegisterOverviewItem::buildingPurpose)
                    .containsExactly("공동주택");
        }
    }

    @Nested
    @DisplayName("fetchBuildingRegisterFloor 메서드는")
    class FetchBuildingRegisterFloor {

        @Test
        @DisplayName("응답이 없으면 BUILDING_REGISTER_FLOOR_FETCH_ERROR 예외를 던진다")
        void throwExceptionWhenResponseIsNull() {
            // given
            BuildingRegisterRequest request = mock(BuildingRegisterRequest.class);
            mockWebClientEmpty();

            // when & then
            assertThatThrownBy(() -> repository.fetchBuildingRegisterFloor("1", request))
                    .isInstanceOf(BusinessException.class)
                    .hasFieldOrPropertyWithValue("errorCode", ErrorCode.BUILDING_REGISTER_FLOOR_FETCH_ERROR);
        }

        @Test
        @DisplayName("정상적으로 리스트를 반환한다")
        void fetchBuildingRegisterFloorSuccess() {
            // given
            BuildingRegisterRequest request = mock(BuildingRegisterRequest.class);
            BuildingRegisterFloorItem item = new BuildingRegisterFloorItem("15동", "40층", "철근콘크리트", "공동주택", "도시형생활주택");
            mockWebClientResponse(List.of(item));

            // when
            List<BuildingRegisterFloorItem> result = repository.fetchBuildingRegisterFloor("1", request);

            // then
            assertThat(result)
                    .isNotEmpty()
                    .hasSize(1)
                    .extracting(BuildingRegisterFloorItem::floorStructure)
                    .containsExactly("철근콘크리트");
        }
    }

    @Nested
    @DisplayName("fetchBuildingRegisterArea 메서드는")
    class FetchBuildingRegisterArea {

        @Test
        @DisplayName("응답이 없으면 BUILDING_REGISTER_AREA_FETCH_ERROR 예외를 던진다")
        void throwExceptionWhenResponseIsNull() {
            // given
            BuildingRegisterRequest request = mock(BuildingRegisterRequest.class);
            mockWebClientEmpty();

            // when & then
            assertThatThrownBy(() -> repository.fetchBuildingRegisterArea("1", request))
                    .isInstanceOf(BusinessException.class)
                    .hasFieldOrPropertyWithValue("errorCode", ErrorCode.BUILDING_REGISTER_AREA_FETCH_ERROR);
        }

        @Test
        @DisplayName("정상적으로 리스트를 반환한다")
        void fetchBuildingRegisterAreaSuccess() {
            // given
            BuildingRegisterRequest request = mock(BuildingRegisterRequest.class);
            BuildingRegisterAreaItem item = new BuildingRegisterAreaItem("40동", "501호", "전유", "84.5");
            mockWebClientResponse(List.of(item));

            // when
            List<BuildingRegisterAreaItem> result = repository.fetchBuildingRegisterArea("1", request);

            // then
            assertThat(result)
                    .isNotEmpty()
                    .hasSize(1)
                    .extracting(BuildingRegisterAreaItem::useType)
                    .containsExactly("전유");
        }
    }
}
