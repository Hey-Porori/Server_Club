package porori.backend.domain.post.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import porori.backend.domain.post.model.dto.PostCreateRequestDTO;
import porori.backend.domain.post.model.dto.PostResponseDTO;
import porori.backend.domain.post.service.PostService;
import porori.backend.global.common.dto.response.SuccessResponse;

import static porori.backend.global.common.status.SuccessStatus.CREATE_POST;

@Tag(name = "동호회 내 글 작성 및 수정 API")
@RestController
@RequestMapping("/api/clubs/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    @Operation(summary = "글 작성", description = "유저가 동호회 내 글을 작성한다.")
    public SuccessResponse<PostResponseDTO> createPost(@RequestHeader("Authorization") String token,
                                                       @RequestBody PostCreateRequestDTO postCreateRequestDTO) {
        return new SuccessResponse<>(CREATE_POST, postService.createPost(token, postCreateRequestDTO));
    }
}
