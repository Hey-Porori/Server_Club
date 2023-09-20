package porori.backend.domain.member.exception;

import porori.backend.global.common.status.ErrorStatus;
import porori.backend.global.error.exception.BaseException;

public class MemberException extends BaseException {
    public MemberException(ErrorStatus error) {
        super(error);
    }
}
