package porori.backend.global.common.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import porori.backend.global.common.status.ErrorStatus;

@Getter
@JsonPropertyOrder({"statusCode", "error", "message", "description"})
public class FailResponse {
    private int statusCode;
    private ErrorStatus error;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    public FailResponse(ErrorStatus status) {
        this.statusCode = status.getStatusCode();
        this.error = status;
        this.message = status.getMessage();
    }

    public FailResponse(ErrorStatus status, String description) {
        this.statusCode = status.getStatusCode();
        this.error = status;
        this.message = status.getMessage();
        this.description = description;
    }
}
