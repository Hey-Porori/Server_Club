package porori.backend.domain.post.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import porori.backend.domain.post.model.dto.PostCreateRequestDTO;
import porori.backend.domain.post.model.dto.PostResponseDTO;
import porori.backend.domain.post.model.dto.PostSubjectResponseDTO;
import porori.backend.domain.post.service.PostService;
import porori.backend.global.common.dto.response.SuccessResponse;

import java.util.List;

import static porori.backend.global.common.status.SuccessStatus.CREATE_POST;
import static porori.backend.global.common.status.SuccessStatus.SUCCESS;

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

    @GetMapping("/subjects")
    @Operation(summary = "주제 조회", description = "글 작성 시 설정할 수 있는 주제를 조회한다.")
    public SuccessResponse<List<PostSubjectResponseDTO>> getPostSubjects() {
        return new SuccessResponse<>(SUCCESS, postService.getPostSubjects());
    }
}
