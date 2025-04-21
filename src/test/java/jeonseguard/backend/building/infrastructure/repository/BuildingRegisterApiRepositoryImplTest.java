package jeonseguard.backend.building.infrastructure.repository;

import jeonseguard.backend.building.infrastructure.dto.external.BuildingRegisterOverviewItem;
import jeonseguard.backend.building.infrastructure.dto.request.BuildingRegisterRequest;
import jeonseguard.backend.global.config.properties.BuildingProperties;
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
@DisplayName("BuildingRegisterApiRepositoryImpl 단위 테스트")
@SuppressWarnings({"rawtypes", "unchecked"})
class BuildingRegisterApiRepositoryImplTest {

    @Mock
    WebClient webClient;

    @Mock
    WebClient.RequestHeadersUriSpec uriSpec;

    @Mock
    WebClient.RequestHeadersSpec headersSpec;

    @Mock
    WebClient.ResponseSpec responseSpec;

    @Mock
    BuildingProperties buildingProperties;

    @InjectMocks
    BuildingRegisterApiRepositoryImpl repository;

    private String pageNumber1;

    @BeforeEach
    void setUp() {
        when(buildingProperties.overviewUri()).thenReturn("https://example.com");
        when(buildingProperties.serviceKey()).thenReturn("test-key");
        when(buildingProperties.categoryCode()).thenReturn("1");
        when(buildingProperties.listSize()).thenReturn("10");
        pageNumber1 = "1";
    }

    @Nested
    @DisplayName("fetchBuildingRegisterOverview 메서드는")
    class FetchBuildingRegisterOverview {

        @Test
        @DisplayName("OpenApiResponse가 null이면, BUILDING_REGISTER_OVERVIEW_FETCH_ERROR 예외가 발생한다.")
        void throwExceptionWhenResponseIsNull() {
            // given
            BuildingRegisterRequest request = mock(BuildingRegisterRequest.class);
            when(webClient.get()).thenReturn(uriSpec);
            when(uriSpec.uri(any(URI.class))).thenReturn(headersSpec);
            when(headersSpec.retrieve()).thenReturn(responseSpec);
            when(responseSpec.bodyToMono(any(ParameterizedTypeReference.class))).thenReturn(Mono.empty());

            // when & then
            assertThatThrownBy(() -> repository.fetchBuildingRegisterOverview(pageNumber1, request))
                    .isInstanceOf(BusinessException.class)
                    .hasFieldOrPropertyWithValue("errorCode", ErrorCode.BUILDING_REGISTER_OVERVIEW_FETCH_ERROR);
        }

        @Test
        @DisplayName("OpenApiResponse.getItem()이 null이면, BUILDING_REGISTER_OVERVIEW_FETCH_ERROR 예외가 발생한다.")
        void throwExceptionWhenItemIsNull() {
            // given
            BuildingRegisterRequest request = mock(BuildingRegisterRequest.class);
            OpenApiResponse<BuildingRegisterOverviewItem> apiResponse = new OpenApiResponse<>(
                    new OpenApiResponse.Response<>(
                            new OpenApiResponse.Body<>(
                                    new OpenApiResponse.Items<>(
                                            List.of()
                                    )
                            )
                    )
            );
            when(webClient.get()).thenReturn(uriSpec);
            when(uriSpec.uri(any(URI.class))).thenReturn(headersSpec);
            when(headersSpec.retrieve()).thenReturn(responseSpec);
            when(responseSpec.bodyToMono(any(ParameterizedTypeReference.class))).thenReturn(Mono.just(apiResponse));

            // when & then
            assertThatThrownBy(() -> repository.fetchBuildingRegisterOverview(pageNumber1, request))
                    .isInstanceOf(BusinessException.class)
                    .hasFieldOrPropertyWithValue("errorCode", ErrorCode.BUILDING_REGISTER_OVERVIEW_FETCH_ERROR);
        }

        @Test
        @DisplayName("정상적인 요청 시, BuildingRegisterOverviewItem을 반환한다.")
        void fetchBuildingRegisterOverviewSuccessTest() {
            // given
            BuildingRegisterRequest request = mock(BuildingRegisterRequest.class);
            BuildingRegisterOverviewItem item = new BuildingRegisterOverviewItem("100.0", "80.0", "철근콘크리트", "공동주택");
            OpenApiResponse<BuildingRegisterOverviewItem> apiResponse = new OpenApiResponse<>(
                    new OpenApiResponse.Response<>(
                            new OpenApiResponse.Body<>(
                                    new OpenApiResponse.Items<>(List.of(item))
                            )
                    )
            );
            when(webClient.get()).thenReturn(uriSpec);
            when(uriSpec.uri(any(URI.class))).thenReturn(headersSpec);
            when(headersSpec.retrieve()).thenReturn(responseSpec);
            when(responseSpec.bodyToMono(any(ParameterizedTypeReference.class))).thenReturn(Mono.just(apiResponse));

            // when
            BuildingRegisterOverviewItem result = repository.fetchBuildingRegisterOverview(pageNumber1, request);

            // then
            assertThat(result).isNotNull();
            assertThat(result.buildingPurpose()).isEqualTo("공동주택");
        }
    }
}
