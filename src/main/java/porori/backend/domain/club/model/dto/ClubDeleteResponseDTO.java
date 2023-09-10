package porori.backend.domain.club.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import porori.backend.domain.club.model.entity.Club;

@Schema(description = "동호회 삭제 Response : 동호회 삭제 후 얻을 수 있는 정보")
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class ClubDeleteResponseDTO {

    @Schema(description = "동호회 ID", example = "1")
    private Long clubId;

    public static ClubDeleteResponseDTO from(Club club) {
        return ClubDeleteResponseDTO.builder()
                .clubId(club.getClubId())
                .build();
    }
}
