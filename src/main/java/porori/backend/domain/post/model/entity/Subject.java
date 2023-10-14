package porori.backend.domain.post.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Subject {

    DEFAULT("일반"),
    POPULAR("인기글"),
    TOGETHER("같이가요"),
    QUESTION("질문"),
    INFORMATION("정보"),
    EMPTY("");

    private final String subject;
}
