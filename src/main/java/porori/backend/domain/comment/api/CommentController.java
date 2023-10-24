package porori.backend.domain.comment.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import porori.backend.domain.comment.model.dto.CommentCreateRequestDTO;
import porori.backend.domain.comment.model.dto.CommentResponseDTO;
import porori.backend.domain.comment.service.CommentService;
import porori.backend.global.common.dto.response.SuccessResponse;

import static porori.backend.global.common.status.SuccessStatus.CREATE_COMMENT;

@Tag(name = "댓글 작성, 조회, 삭제 API")
@RestController
@RequestMapping("/api/clubs/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create")
    @Operation(summary = "댓글 작성", description = "유저가 동호회에 작성된 글에 대해 댓글을 작성한다.")
    public SuccessResponse<CommentResponseDTO> createComment(@Parameter(hidden = true) @RequestHeader("Authorization") String token,
                                                             @RequestBody CommentCreateRequestDTO commentCreateRequestDTO) {
        return new SuccessResponse<>(CREATE_COMMENT, commentService.createComment(token, commentCreateRequestDTO));
    }
}
