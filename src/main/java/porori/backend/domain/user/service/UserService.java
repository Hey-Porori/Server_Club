package porori.backend.domain.user.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import porori.backend.domain.member.model.dto.MemberResponseDTO;
import porori.backend.domain.user.exception.UserException;
import porori.backend.global.common.status.ErrorStatus;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final WebClient webClient;

    public Long getUserId(String token) {
        String response = sendTokenMeRequest(token);
        return extractUserIdFromResponse(response);
    }

    private String sendTokenMeRequest(String token) {
        try {
            return webClient.get()
                    .uri("/token/me")
                    .header("Authorization", token)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (Exception e) {
            throw new UserException(ErrorStatus.INVALID_JWT);
        }
    }

    private Long extractUserIdFromResponse(String response) {
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        JsonObject data = jsonObject.getAsJsonObject("data");
        return data.get("userId").getAsLong();
    }

    public List<MemberResponseDTO> getMemberResponseDTO(List<Long> userIds) {
        String response = sendCommunitiesInfoRequest(userIds);
        return extractUserInfoFromResponse(response);
    }

    private String sendCommunitiesInfoRequest(List<Long> userIds) {
        try {
            StringBuilder userIdList = new StringBuilder("/communities/info?userIdList=");
            for (Long userId : userIds) {
                userIdList.append(userId).append(",");
            }
            userIdList.delete(userIdList.length() - 1, userIdList.length());

            return webClient.get()
                    .uri(userIdList.toString())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (Exception e) {
            throw new UserException(ErrorStatus.NOT_EXIST_USER);
        }
    }

    private List<MemberResponseDTO> extractUserInfoFromResponse(String response) {
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        JsonObject data = jsonObject.getAsJsonObject("data");

        List<MemberResponseDTO> memberResponseDTOs = new ArrayList<>();
        for (JsonElement element : data.getAsJsonArray("communityUserInfoBlocks")) {
            JsonObject userInfo = element.getAsJsonObject();
            memberResponseDTOs.add(MemberResponseDTO.from(userInfo));
        }
        return memberResponseDTOs;
    }

}
