package porori.backend.domain.comment.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "댓글 작성 Request: 동호회 댓글 작성 시 필요한 정보")
@NoArgsConstructor
@Getter
public class CommentCreateRequestDTO {

    @Schema(description = "글 ID", example = "1")
    private Long postId;

    @Schema(description = "댓글 내용", example = "진짜 맛있어 보인다.. 저도 먹고 싶어요")
    private String content;
}
