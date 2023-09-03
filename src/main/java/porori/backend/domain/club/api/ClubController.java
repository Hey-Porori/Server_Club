package porori.backend.domain.club.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import porori.backend.domain.club.model.dto.ClubCreateRequestDTO;
import porori.backend.domain.club.model.dto.ClubCreateResponseDTO;
import porori.backend.domain.club.model.dto.ClubGetResponseDTO;
import porori.backend.domain.club.service.ClubService;
import porori.backend.global.common.dto.response.SuccessResponse;

import java.util.List;

import static porori.backend.global.common.status.SuccessStatus.CREATE_CLUB;
import static porori.backend.global.common.status.SuccessStatus.SUCCESS;

@Tag(name = "동호회 생성 및 수정 API")
@RestController
@RequestMapping("/api/clubs")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;

    @PostMapping(value = "/create")
    @Operation(summary = "동호회 생성", description = "유저가 동호회를 생성한다.")
    public SuccessResponse<ClubCreateResponseDTO> createClub(@RequestHeader("Authorization") String token,
                                                            @RequestBody ClubCreateRequestDTO clubCreateRequestDTO) {
        return new SuccessResponse<>(CREATE_CLUB, clubService.createClub(token, clubCreateRequestDTO));
    }

    @GetMapping("/all")
    @Operation(summary = "동호회 전체 조회", description = "동호회 전체를 조회한다.")
    public SuccessResponse<List<ClubGetResponseDTO>> getAllClubs() {
        return new SuccessResponse<>(SUCCESS, clubService.getAllClubs());
    }

}
