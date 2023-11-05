package porori.backend.domain.club.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import porori.backend.domain.club.model.entity.Club;

@Schema(description = "동호회 정보 조회 Response : 동호회 조회 시 얻을 수 있는 정보")
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class ClubGetResponseDTO {

    @Schema(description = "동호회 ID", example = "1")
    private Long clubId;

    @Schema(description = "동호회 이름", example = "산타는 할아버지")
    private String name;

    @Schema(description = "동호회 주요 주제", example = "운동")
    private String subjectTitle;

    @Schema(description = "동호회 세부 주제", example = "하이킹")
    private String subjectDetail;

    @Schema(description = "동호회 주요 활동 위치", example = "거여동")
    private String location;

    @Schema(description = "동호회 최대 수용 인원", example = "10")
    private int limitMemberNumber;

    @Schema(description = "동호회 현재 인원", example = "10")
    private int currentMemberNumber;

    @Schema(description = "동호회 설명", example = "즐겁게 등산합시다")
    private String description;

    public static ClubGetResponseDTO from(Club club) {
        return ClubGetResponseDTO.builder()
                .clubId(club.getClubId())
                .name(club.getName())
                .subjectTitle(club.getSubjectTitle().getTitle())
                .subjectDetail(club.getSubjectDetail().getDetail())
                .location(club.getLocation())
                .limitMemberNumber(club.getLimitMemberNumber())
                .currentMemberNumber(club.getCurrentMemberNumber())
                .description(club.getDescription())
                .build();
    }

}
