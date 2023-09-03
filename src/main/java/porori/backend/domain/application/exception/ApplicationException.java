package porori.backend.domain.application.exception;

import porori.backend.global.common.status.ErrorStatus;
import porori.backend.global.error.exception.BaseException;

public class ApplicationException extends BaseException {
    public ApplicationException(ErrorStatus error) {
        super(error);
    }
}
