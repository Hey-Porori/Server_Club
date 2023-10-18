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
    INVALID_POST(400, "유효하지 않은 글 ID입니다."),
    INVALID_SUBJECT_TITLE(400, "유효하지 않은 동호회 주제입니다."),
    INVALID_POST_SUBJECT(400, "유효하지 않은 글 주제입니다."),
    EXIST_APPLICATION(400, "이미 가입 신청한 동호회입니다."),
    FULL_CLUB_NUMBER(400, "동호회 인원이 모두 찼습니다."),

    INVALID_JWT(401, "유효하지 않은 JWT입니다."),

    NOT_CLUB_MANAGER(403, "현재 접속한 유저가 관리하는 동호회가 아닙니다."),
    MANAGER_CANT_QUIT(403, "동호회 관리자는 동호회를 탈퇴할 수 없습니다."),
    NOT_CLUB_POST(403, "해당 동호회의 글의 아닙니다."),

    NOT_EXIST_APPLICATION(404, "신청 내역이 존재하지 않습니다."),
    NOT_EXIST_MEMBER(404, "동호회 회원이 아닙니다."),
    NOT_EXIST_USER(404, "유저 정보가 존재하지 않습니다.");

    private final int statusCode;
    private final String message;
}
