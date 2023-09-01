package porori.backend.global.common.status;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorStatus {

    /**
     * Error Code
     * 400:
     * 401: JWT 오류
     */

    INVALID_CLUB(400, "유효하지 않은 동호회 ID입니다."),

    INVALID_JWT(401, "유효하지 않은 JWT입니다.");

    private final int statusCode;
    private final String message;
}
