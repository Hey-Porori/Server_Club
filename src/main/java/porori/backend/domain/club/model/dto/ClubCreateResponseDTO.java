package porori.backend.domain.club.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import porori.backend.domain.club.model.entity.Club;

@Builder(access = AccessLevel.PRIVATE)
@Getter
public class ClubCreateResponseDTO {
    private Long clubId;

    public static ClubCreateResponseDTO from(Club club) {
        return ClubCreateResponseDTO.builder()
                .clubId(club.getClubId())
                .build();
    }
}
