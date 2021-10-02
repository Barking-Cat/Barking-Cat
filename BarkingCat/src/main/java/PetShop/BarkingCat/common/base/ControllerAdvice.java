package PetShop.BarkingCat.common.base;

import PetShop.BarkingCat.common.base.exception.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<?> handleRuntimeException(RuntimeException exception) {
        logger.error(exception.getMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(exception.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<?> handleAuthenticationException(AuthenticationException exception) {
        logger.error(exception.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(exception.getMessage());
    }
}
