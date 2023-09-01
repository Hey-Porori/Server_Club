package porori.backend.domain.application.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationStatus {
    APPLIED("가입 신청"),
    COMPLETED("가입 완료"),
    REJECTED("가입 거절");

    private final String status;
}
