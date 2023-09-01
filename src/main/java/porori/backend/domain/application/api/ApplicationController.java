package porori.backend.domain.application.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import porori.backend.domain.application.model.dto.ApplicationCreateRequestDTO;
import porori.backend.domain.application.model.dto.ApplicationCreateResponseDTO;
import porori.backend.domain.application.service.ApplicationService;
import porori.backend.global.common.dto.response.SuccessResponse;

import static porori.backend.global.common.status.SuccessStatus.CREATE_APPLICATION;

@Tag(name = "동호회 가입 신청 및 취소 API")
@RestController
@RequestMapping("/api/clubs/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/create")
    @Operation(summary = "동호회 가입 신청", description = "유저가 동호회에 가입 신청을 한다.")
    public SuccessResponse<ApplicationCreateResponseDTO> createApplication(@RequestHeader("Authorization") String token,
                                                                           @RequestBody ApplicationCreateRequestDTO applicationCreateRequestDTO) {
        return new SuccessResponse<>(CREATE_APPLICATION, applicationService.createApplication(token, applicationCreateRequestDTO));
    }
}
