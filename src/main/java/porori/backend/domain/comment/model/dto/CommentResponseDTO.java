package porori.backend.domain.comment.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import porori.backend.domain.comment.model.entity.Comment;
import porori.backend.domain.member.model.dto.MemberResponseDTO;

@Schema(description = "동호회 댓글 Response: 동호회 댓글 조회 시 얻을 수 있는 정보")
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class CommentResponseDTO {

    @Schema(description = "댓글 ID", example = "1")
    private Long commentId;

    @Schema(description = "유저 닉네임", example = "세종대왕")
    private String nickName;

    @Schema(description = "유저 프로필 사진 url", example = "picture.png")
    private String imageUrl;

    @Schema(description = "해당 글 작성 여부", example = "true")
    private boolean isWriter;

    @Schema(description = "댓글 내용", example = "저도 같이 갈래요")
    private String content;

    public static CommentResponseDTO of(Comment comment, MemberResponseDTO memberResponseDTO, boolean isWriter) {
        return CommentResponseDTO.builder()
                .commentId(comment.getCommentId())
                .nickName(memberResponseDTO.getNickName())
                .imageUrl(memberResponseDTO.getImageUrl())
                .isWriter(isWriter)
                .content(comment.getContent())
                .build();
    }
}
