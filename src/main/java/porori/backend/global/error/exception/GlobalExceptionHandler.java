package porori.backend.global.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import porori.backend.domain.application.exception.ApplicationException;
import porori.backend.domain.club.exception.ClubException;
import porori.backend.domain.user.exception.UserException;
import porori.backend.global.common.dto.response.FailResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<FailResponse> handleBaseException(BaseException exception) {
        return ResponseEntity.status(HttpStatus.valueOf(exception.getError().getStatusCode())).body(new FailResponse(exception.getError()));
    }

    @ExceptionHandler({UserException.class})
    protected ResponseEntity<FailResponse> handleUserException(UserException exception) {
        return ResponseEntity.status(HttpStatus.valueOf(exception.getError().getStatusCode())).body(new FailResponse(exception.getError()));
    }

    @ExceptionHandler({ClubException.class})
    protected ResponseEntity<FailResponse> handleClubException(ClubException exception) {
        return ResponseEntity.status(HttpStatus.valueOf(exception.getError().getStatusCode())).body(new FailResponse(exception.getError()));
    }

    @ExceptionHandler({ApplicationException.class})
    protected ResponseEntity<FailResponse> handleApplicationException(ApplicationException exception) {
        return ResponseEntity.status(HttpStatus.valueOf(exception.getError().getStatusCode())).body(new FailResponse(exception.getError()));
    }
}
