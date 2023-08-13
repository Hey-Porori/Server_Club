package porori.backend.domain.club.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class ClubCreateRequestDTO {
    private String name;
    private String subjectTitle;
    private String subjectDetail;
    private String location;
    private int limitMemberNumber;
    private List<String> qualifications;

    @Builder
    public ClubCreateRequestDTO(String name, String subjectTitle, String subjectDetail, String location, int limitMemberNumber, List<String> qualifications) {
        this.name = name;
        this.subjectTitle = subjectTitle;
        this.subjectDetail = subjectDetail;
        this.location = location;
        this.limitMemberNumber = limitMemberNumber;
        this.qualifications = qualifications;
    }
}
