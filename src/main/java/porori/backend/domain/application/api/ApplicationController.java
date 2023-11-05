package porori.backend.domain.application.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import porori.backend.domain.application.model.dto.ApplicationResponseDTO;
import porori.backend.domain.application.service.ApplicationService;
import porori.backend.global.common.dto.response.SuccessResponse;

import java.util.List;

import static porori.backend.global.common.status.SuccessStatus.*;

@Tag(name = "동호회 가입 신청 및 취소 API")
@RestController
@RequestMapping("/api/clubs/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/create/{clubId}")
    @Operation(summary = "동호회 가입 신청", description = "유저가 동호회에 가입 신청을 한다.")
    public SuccessResponse<ApplicationResponseDTO> createApplication(@Parameter(hidden = true) @RequestHeader("Authorization") String token,
                                                                     @PathVariable Long clubId) {
        return new SuccessResponse<>(CREATE_APPLICATION, applicationService.createApplication(token, clubId));
    }

    @PostMapping("/accept/{clubId}/{userId}")
    @Operation(summary = "동호회 가입 수락", description = "관리자가 가입 신청한 유저를 수락한다.")
    public SuccessResponse<ApplicationResponseDTO> acceptApplication(@Parameter(hidden = true) @RequestHeader("Authorization") String token,
                                                                     @PathVariable Long clubId,
                                                                     @PathVariable Long userId) {
        return new SuccessResponse<>(SUCCESS, applicationService.acceptApplication(token, clubId, userId));
    }

    @PostMapping("/reject/{clubId}/{userId}")
    @Operation(summary = "동호회 가입 거절", description = "관리자가 가입 신청한 유저를 거절한다.")
    public SuccessResponse<ApplicationResponseDTO> rejectApplication(@Parameter(hidden = true) @RequestHeader("Authorization") String token,
                                                                     @PathVariable Long clubId,
                                                                     @PathVariable Long userId) {
        return new SuccessResponse<>(SUCCESS, applicationService.rejectApplication(token, clubId, userId));
    }

    @GetMapping("/list/{clubId}")
    @Operation(summary = "동호회 가입 신청자 확인", description = "관리자가 가입 신청한 유저 목록을 조회한다.")
    public SuccessResponse<List<ApplicationResponseDTO>> getApplicationList(@Parameter(hidden = true) @RequestHeader("Authorization") String token,
                                                                            @PathVariable Long clubId) {
        return new SuccessResponse<>(SUCCESS, applicationService.getApplicationList(token, clubId));
    }
}
