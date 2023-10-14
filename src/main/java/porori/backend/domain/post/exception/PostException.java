package porori.backend.domain.post.exception;

import porori.backend.global.common.status.ErrorStatus;
import porori.backend.global.error.exception.BaseException;

public class PostException extends BaseException {
    public PostException(ErrorStatus error) {
        super(error);
    }
}
