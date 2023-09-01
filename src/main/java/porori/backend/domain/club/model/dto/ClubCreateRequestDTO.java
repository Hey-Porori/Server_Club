package porori.backend.domain.club.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ClubCreateRequestDTO {
    private String name;
    private String subjectTitle;
    private String subjectDetail;
    private String location;
    private int limitMemberNumber;
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
