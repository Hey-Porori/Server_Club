package porori.backend.domain.member.model.dto;

import com.google.gson.JsonObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

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

    public static MemberResponseDTO from(JsonObject userInfo) {
        return MemberResponseDTO.builder()
                .userId(userInfo.get("userId").getAsLong())
                .nickName(userInfo.get("nickname").getAsString())
                .imageUrl(userInfo.get("image").getAsString())
                .build();
    }
}
