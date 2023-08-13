package porori.backend.global.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import porori.backend.domain.user.exception.UserException;
import porori.backend.global.common.dto.response.FailResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<FailResponse> handleBaseException(BaseException exception) {
        return ResponseEntity.status(HttpStatus.valueOf(exception.getError().getCode())).body(new FailResponse(exception.getError()));
    }

    @ExceptionHandler({UserException.class})
    protected ResponseEntity<FailResponse> handleUserException(UserException exception) {
        return ResponseEntity.status(HttpStatus.valueOf(exception.getError().getCode())).body(new FailResponse(exception.getError()));
    }
}