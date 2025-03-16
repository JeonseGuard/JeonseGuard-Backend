package jeonseguard.backend.global.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.global.exception.error.ErrorCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
        @Schema(description = "HTTP 상태 코드", example = "500") int status,
        @Schema(description = "에러 메시지", example = "서버 내부 오류입니다.") String message
) {
    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getStatus().value(), errorCode.getMessage());
    }
}
