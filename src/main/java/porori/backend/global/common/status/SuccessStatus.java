package porori.backend.global.common.status;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessStatus {

    CREATE_CLUB(201, "동호회 등록이 완료되었습니다.");

    private final int statusCode;
    private final String message;
}