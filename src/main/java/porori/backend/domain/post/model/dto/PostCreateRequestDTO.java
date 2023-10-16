package porori.backend.domain.post.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "글 작성 Request : 동호회 글 작성 시 필요한 정보")
@NoArgsConstructor
@Getter
public class PostCreateRequestDTO {

    @Schema(description = "동호회 ID", example = "1")
    private Long clubId;

    @Schema(description = "글 제목", example = "등산 가실 분")
    private String title;

    @Schema(description = "글 내용", example = "집현전 앞에서 만납시다")
    private String content;

    @Schema(description = "공지 여부", example = "true")
    private boolean isImportant;

    @Schema(description = "글 주제", example = "같이가요")
    private String subject;
}
