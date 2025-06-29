package jeonseguard.backend.global.exception.error;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 회원 관련 에러
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다."),
    USER_SUMMARY_NOT_FOUND(HttpStatus.NOT_FOUND, "회원 요약 정보를 찾을 수 없습니다."),

    // 인증 관련 예외
    INVALID_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "잘못된 JWT 토큰입니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "잘못된 리프레시 토큰입니다."),
    TOKEN_IS_BLACKLISTED(HttpStatus.UNAUTHORIZED, "블랙리스트된 엑세스 토큰입니다."),

    // 카카오 로그인 관련 에러
    INVALID_KAKAO_TOKEN(HttpStatus.UNAUTHORIZED, "잘못된 카카오 액세스 토큰입니다."),
    INVALID_KAKAO_AUTHORIZATION_CODE(HttpStatus.BAD_REQUEST, "잘못된 카카오 인가 코드입니다."),
    KAKAO_TOKEN_FETCH_FAILED(HttpStatus.UNAUTHORIZED, "카카오 토큰을 가져오는 데 실패했습니다."),
    KAKAO_USER_INFO_FETCH_FAILED(HttpStatus.UNAUTHORIZED, "카카오 사용자 정보를 가져오는 데 실패했습니다."),

    // 게시글 관련 에러
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."),
    POST_SUMMARY_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글 요약 정보를 찾을 수 없습니다."),
    POST_UPDATE_FORBIDDEN(HttpStatus.FORBIDDEN, "게시글을 수정할 권한이 없습니다."),
    POST_DELETE_FORBIDDEN(HttpStatus.FORBIDDEN, "게시글을 삭제할 권한이 없습니다."),

    // 댓글 관련 에러
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."),
    COMMENT_UPDATE_FORBIDDEN(HttpStatus.FORBIDDEN, "댓글을 수정할 권한이 없습니다."),
    COMMENT_DELETE_FORBIDDEN(HttpStatus.FORBIDDEN, "댓글을 삭제할 권한이 없습니다."),

    // 행정구역 관련 에러
    REGION_SUMMARY_NOT_FOUND(HttpStatus.NOT_FOUND, "행정구역 요약 정보를 찾을 수 없습니다."),
    REGION_DELETE_FORBIDDEN(HttpStatus.FORBIDDEN, "행정구역을 삭제할 권한이 없습니다."),
    INVALID_REGION_CODE(HttpStatus.BAD_REQUEST, "유효하지 않은 법정동코드입니다."),

    // 주소 관련 에러
    INVALID_DONG_NAME_FORMAT(HttpStatus.BAD_REQUEST, "동명칭의 형식이 올바르지 않습니다."),
    INVALID_FLOOR_NAME_FORMAT(HttpStatus.BAD_REQUEST, "층명칭의 형식이 올바르지 않습니다."),
    INVALID_HO_NAME_FORMAT(HttpStatus.BAD_REQUEST, "호명칭의 형식이 올바르지 않습니다."),
    INVALID_BUNJI_FORMAT(HttpStatus.BAD_REQUEST, "번지/지번의 형식이 올바르지 않습니다."),

    // 건축물대장 표제부 조회 관련 에러
    BUILDING_OVERVIEW_INFO_NOT_FOUND(HttpStatus.NOT_FOUND, "건축물대장 표제부 정보를 찾을 수 없습니다."),
    BUILDING_REGISTER_OVERVIEW_FETCH_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "건축물대장 표제부 정보를 가져오는 데 실패했습니다."),

    // 건축물대장 층별 조회 관련 에러
    BUILDING_FLOOR_INFO_NOT_FOUND(HttpStatus.NOT_FOUND, "건축물대장 층별 정보를 찾을 수 없습니다."),
    BUILDING_REGISTER_FLOOR_FETCH_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "건축물대장 층별 정보를 가져오는 데 실패했습니다."),

    // 건축물대장 전유/공용면적 조회 관련 에러
    BUILDING_AREA_INFO_NOT_FOUND(HttpStatus.NOT_FOUND, "건축물대장 전유/공용면적 정보를 찾을 수 없습니다."),
    BUILDING_REGISTER_AREA_FETCH_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "건축물대장 전유/공용면적 정보를 가져오는 데 실패했습니다."),

    // 네이버 뉴스 관련 에러
    NAVER_NEWS_FETCH_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "네이버 뉴스를 가져오는 데 실패했습니다."),

    // 기타 서버 오류
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "리소스를 찾을 수 없습니다."),
    CONFLICT(HttpStatus.CONFLICT, "이미 존재하는 데이터입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다.");

    private final HttpStatus status;
    private final String message;
}
