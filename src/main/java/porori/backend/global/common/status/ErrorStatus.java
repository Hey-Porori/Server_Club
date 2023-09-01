package porori.backend.global.common.status;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorStatus {

    /**
     * Error Code
     * 400: BAD_REQUEST
     * 401: UNAUTHORIZED
     * 403: FORBIDDEN
     * 404: NOT_FOUND
     */

    INVALID_CLUB(400, "유효하지 않은 동호회 ID입니다."),

    EXIST_APPLICATION(400, "이미 가입 신청한 동호회입니다."),

    INVALID_JWT(401, "유효하지 않은 JWT입니다.");

    private final int statusCode;
    private final String message;
}
