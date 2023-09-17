package porori.backend.domain.member.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import porori.backend.domain.member.model.entity.Member;

@Schema(description = "동호회 회원 조회 Response : 동호회 회원 조회 시 얻을 수 있는 정보")
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class MemberResponseDTO {

    @Schema(description = "유저 ID", example = "1")
    private Long userId;

    @Schema(description = "유저 닉네임", example = "홍길동")
    private String nickName;

    @Schema(description = "유저 프로필 이미지 URL", example = "profile.png")
    private String imageUrl;

    // todo: userId로 정보를 얻어와서 DTO로 변환
    public static MemberResponseDTO from(Member member) {
        return MemberResponseDTO.builder()
                .userId(member.getUserId())
                .build();
    }
}
