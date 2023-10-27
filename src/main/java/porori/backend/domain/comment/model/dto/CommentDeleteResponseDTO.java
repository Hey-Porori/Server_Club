package porori.backend.domain.comment.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import porori.backend.domain.comment.model.entity.Comment;

@Schema(description = "댓글 삭제 Response : 댓글 삭제 후 얻을 수 있는 정보")
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class CommentDeleteResponseDTO {

    @Schema(description = "댓글 ID", example = "1")
    private Long commentId;

    public static CommentDeleteResponseDTO from(Comment comment) {
        return CommentDeleteResponseDTO.builder()
                .commentId(comment.getCommentId())
                .build();
    }
}
