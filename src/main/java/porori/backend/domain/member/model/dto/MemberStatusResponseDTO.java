package porori.backend.domain.member.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import porori.backend.domain.member.model.entity.Member;

@Schema(description = "동호회 회원 탈퇴 Response : 동호회 회원 탈퇴 시 얻을 수 있는 정보")
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class MemberStatusResponseDTO {

    @Schema(description = "동호회 ID", example = "1")
    private Long clubId;

    @Schema(description = "유저 ID", example = "1")
    private Long userId;

    public static MemberStatusResponseDTO from(Member member) {
        return MemberStatusResponseDTO.builder()
                .clubId(member.getClub().getClubId())
                .userId(member.getUserId())
                .build();
    }
}
