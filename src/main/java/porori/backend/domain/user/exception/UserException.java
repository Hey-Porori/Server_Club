package porori.backend.domain.user.exception;

import porori.backend.global.common.status.ErrorStatus;
import porori.backend.global.error.exception.BaseException;

public class UserException extends BaseException {
    public UserException(ErrorStatus error) {
        super(error);
    }
}
