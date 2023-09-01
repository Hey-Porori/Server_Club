package porori.backend.domain.user.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import porori.backend.domain.user.exception.UserException;
import porori.backend.global.common.status.ErrorStatus;

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

}
