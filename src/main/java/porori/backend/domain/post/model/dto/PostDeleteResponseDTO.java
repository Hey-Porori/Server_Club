package porori.backend.domain.post.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import porori.backend.domain.post.model.entity.Post;

@Schema(description = "글 삭제 Response : 글 삭제 후 얻을 수 있는 정보")
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class PostDeleteResponseDTO {

    @Schema(description = "글 ID", example = "1")
    private Long postId;

    public static PostDeleteResponseDTO from(Post post) {
        return PostDeleteResponseDTO.builder()
                .postId(post.getPostId())
                .build();
    }
}
