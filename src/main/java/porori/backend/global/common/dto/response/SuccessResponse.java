package porori.backend.global.common.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import porori.backend.global.common.status.SuccessStatus;

@Getter
@JsonPropertyOrder({"statusCode", "message", "data"})
public class SuccessResponse<T> {
    private int statusCode;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public SuccessResponse(SuccessStatus status, T data) {
        this.statusCode = status.getStatusCode();
        this.message = status.getMessage();
        this.data = data;
    }
}
