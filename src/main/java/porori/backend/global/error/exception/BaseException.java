package porori.backend.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import porori.backend.global.common.status.ErrorStatus;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private ErrorStatus error;
}
