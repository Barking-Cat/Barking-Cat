package PetShop.BarkingCat.common.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    @Builder
    public ErrorResponse(int status, String error, String code, String message) {
        this.status = status;
        this.error = error;
        this.code = code;
        this.message = message;
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.httpStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.httpStatusValue())
                        .error(errorCode.httpStatusName())
                        .code(errorCode.name())
                        .message(errorCode.detail())
                        .build()
                );
    }
}