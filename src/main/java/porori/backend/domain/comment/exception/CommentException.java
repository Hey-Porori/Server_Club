package porori.backend.domain.comment.exception;

import porori.backend.global.common.status.ErrorStatus;
import porori.backend.global.error.exception.BaseException;

public class CommentException extends BaseException {
    public CommentException(ErrorStatus error) {
        super(error);
    }
}
