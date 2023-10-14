package porori.backend.domain.post.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

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

    public static Subject valueOfSubject(String subject) {
        return Arrays.stream(Subject.values())
                .filter(Subject -> Objects.equals(subject, Subject.getSubject()))
                .findFirst()
                .orElse(EMPTY);
    }
}
