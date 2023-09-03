package porori.backend.domain.club.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "동호회 생성 Request : 동호회 생성 시 필요한 정보")
@NoArgsConstructor
@Getter
public class ClubCreateRequestDTO {

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

    @Schema(description = "동호회 설명", example = "즐겁게 등산합시다")
    private String description;

    @Builder
    public ClubCreateRequestDTO(String name, String subjectTitle, String subjectDetail, String location, int limitMemberNumber, String description) {
        this.name = name;
        this.subjectTitle = subjectTitle;
        this.subjectDetail = subjectDetail;
        this.location = location;
        this.limitMemberNumber = limitMemberNumber;
        this.description = description;
    }
}
