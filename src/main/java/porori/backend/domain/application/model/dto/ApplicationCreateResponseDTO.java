package porori.backend.domain.application.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import porori.backend.domain.application.model.entity.Application;
import porori.backend.domain.application.model.entity.ApplicationStatus;

@Schema(description = "동호회 가입 신청 Response : 동호회 가입 신청 후 얻을 수 있는 정보")
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class ApplicationCreateResponseDTO {

    @Schema(description = "유저 ID", example = "1")
    private Long userId;

    @Schema(description = "동호회 ID", example = "1")
    private Long clubId;

    @Schema(description = "지원 상태", example = "APPLIED")
    private ApplicationStatus applicationStatus;

    public static ApplicationCreateResponseDTO from(Application application) {
        return ApplicationCreateResponseDTO.builder()
                .userId(application.getUserId())
                .clubId(application.getClub().getClubId())
                .applicationStatus(application.getApplicationStatus())
                .build();
    }
}
