package PetShop.BarkingCat.common.exception;

import org.springframework.http.HttpStatus;

public class BarkingCatException extends RuntimeException {
    private final ErrorCode errorCode;

    public BarkingCatException(ErrorCode errorCode) {
        super(errorCode.detail());
        this.errorCode = errorCode;
    }

    public ErrorCode errorCode() {
        return this.errorCode;
    }

    public HttpStatus status() {
        return errorCode.httpStatus();
    }
}