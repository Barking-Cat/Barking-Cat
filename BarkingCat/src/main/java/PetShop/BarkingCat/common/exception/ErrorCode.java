package PetShop.BarkingCat.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    /* 400 BAD_REQUEST : 잘못된 요청 */
    CREATE_TOKEN_FAIL(HttpStatus.BAD_REQUEST, "토큰 생성에 실패했습니다"),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "토큰 정보가 유효하지 않습니다"),
    TOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST, "토큰이 존재하지 않습니다"),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    UNAUTHORIZED_MEMBER(HttpStatus.UNAUTHORIZED, "권한이 없는 사용자입니다"),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저 정보를 찾을 수 없습니다"),
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "게시물을 찾을 수 없습니다"),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다"),
    ADOPT_REQUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "입양신청서를 찾을 수 없습니다"),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "카테고리를 찾을 수 없습니다"),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이메일이 이미 존재합니다"),
    TEMP_EMAIL_EXIST(HttpStatus.CONFLICT, "이메일이 승인 대기중입니다"),
    ALREADY_LIKED(HttpStatus.CONFLICT, "이미 좋아요를 했습니다"),

    /* 500 Internal Server Error : 서버 내부 문제 */
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부에 문제가 발생했습니다"),
    JWT_ALGORITHM_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "JWT 알고리즘이 존재하지 않습니다"),
    JSON_PROCESSING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "JSON 파싱에 실패했습니다")
    ;

    private final HttpStatus httpStatus;
    private final String detail;

    ErrorCode(HttpStatus httpStatus, String detail) {
        this.httpStatus = httpStatus;
        this.detail = detail;
    }

    public String detail() {
        return this.detail;
    }

    public HttpStatus httpStatus() {
        return this.httpStatus;
    }
}