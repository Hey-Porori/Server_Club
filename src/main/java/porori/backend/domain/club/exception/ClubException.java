package porori.backend.domain.club.exception;

import porori.backend.global.common.status.ErrorStatus;
import porori.backend.global.error.exception.BaseException;

public class ClubException extends BaseException {
    public ClubException(ErrorStatus error) {
        super(error);
    }
}
