package porori.backend.domain.club.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Schema(description = "동호회 주제 Response : 동호회 주제/세부주제 조회 시 얻을 수 있는 정보")
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class ClubSubjectResponseDTO {

    @Schema(description = "동호회 주제 이름", example = "여행")
    private String subjectTitle;

    @Schema(description = "동호회 세부 주제 이름", example = "캠핑")
    private List<String> subjectDetails;

    public static ClubSubjectResponseDTO of(Map.Entry<String, List<String>> entry) {
        return ClubSubjectResponseDTO.builder()
                .subjectTitle(entry.getKey())
                .subjectDetails(entry.getValue())
                .build();
    }
}
