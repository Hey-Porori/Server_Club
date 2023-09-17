package porori.backend.domain.member.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import porori.backend.domain.member.model.dto.MemberResponseDTO;
import porori.backend.domain.member.service.MemberService;
import porori.backend.global.common.dto.response.SuccessResponse;

import java.util.List;

import static porori.backend.global.common.status.SuccessStatus.SUCCESS;

@Tag(name = "동호회 멤버 API")
@RestController
@RequestMapping("/api/clubs/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/list/{clubId}")
    @Operation(summary = "동호회 회원 확인", description = "관리자가 동호회 회원 목록을 조회한다.")
    public SuccessResponse<List<MemberResponseDTO>> getMemberList(@RequestHeader("Authorization") String token,
                                                                  @PathVariable Long clubId) {
        return new SuccessResponse<>(SUCCESS, memberService.getMemberList(token, clubId));
    }

}
