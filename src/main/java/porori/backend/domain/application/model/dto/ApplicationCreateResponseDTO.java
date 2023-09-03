package porori.backend.domain.application.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import porori.backend.domain.application.model.entity.Application;
import porori.backend.domain.application.model.entity.ApplicationStatus;

@Builder(access = AccessLevel.PRIVATE)
@Getter
public class ApplicationCreateResponseDTO {

    private Long userId;
    private Long clubId;
    private ApplicationStatus applicationStatus;

    public static ApplicationCreateResponseDTO from(Application application) {
        return ApplicationCreateResponseDTO.builder()
                .userId(application.getUserId())
                .clubId(application.getClub().getClubId())
                .applicationStatus(application.getApplicationStatus())
                .build();
    }
}
