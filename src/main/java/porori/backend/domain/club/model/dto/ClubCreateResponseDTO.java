package porori.backend.domain.club.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import porori.backend.domain.club.model.entity.Club;

@Schema(description = "동호회 생성 Response : 동호회 생성 후 얻을 수 있는 정보")
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class ClubCreateResponseDTO {

    @Schema(description = "동호회 ID", example = "1")
    private Long clubId;

    public static ClubCreateResponseDTO from(Club club) {
        return ClubCreateResponseDTO.builder()
                .clubId(club.getClubId())
                .build();
    }
}
