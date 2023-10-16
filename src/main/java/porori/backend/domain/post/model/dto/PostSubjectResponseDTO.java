package porori.backend.domain.post.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import porori.backend.domain.post.model.entity.Subject;

@Schema(description = "글 주제 Response : 글 주제 조회 시 얻을 수 있는 정보")
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class PostSubjectResponseDTO {

    @Schema(description = "글 주제", example = "일반")
    private String subject;

    public static PostSubjectResponseDTO from(Subject subject) {
        return PostSubjectResponseDTO.builder()
                .subject(subject.getSubject())
                .build();
    }
}
