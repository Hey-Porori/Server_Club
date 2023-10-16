package porori.backend.domain.post.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import porori.backend.domain.member.model.dto.MemberResponseDTO;
import porori.backend.domain.post.model.entity.Post;

@Schema(description = "동호회 글 Response : 동호회 글 조회 시 얻을 수 있는 정보")
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class PostResponseDTO {

    @Schema(description = "글 ID", example = "1")
    private Long postId;

    @Schema(description = "유저 닉네임", example = "세종대왕")
    private String nickName;

    @Schema(description = "글 제목", example = "등산 가실 분")
    private String title;

    @Schema(description = "글 내용", example = "집현전 앞에서 모입시다")
    private String content;

    @Schema(description = "공지 여부", example = "true")
    private boolean isImportant;

    @Schema(description = "글 주제", example = "같이가요")
    private String subject;

    public static PostResponseDTO of(Post post, MemberResponseDTO memberResponseDTO) {
        return PostResponseDTO.builder()
                .postId(post.getPostId())
                .nickName(memberResponseDTO.getNickName())
                .title(post.getTitle())
                .content(post.getContent())
                .isImportant(post.isImportant())
                .subject(post.getSubject().getSubject())
                .build();
    }
}
