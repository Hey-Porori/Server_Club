package porori.backend.domain.club.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum SubjectTitle {
    TRAVEL("여행"),
    COOK("요리"),
    LANGUAGE("언어"),
    SPORT("운동"),
    ART("미술"),
    SCIENCE("과학"),
    MUSIC("음악"),
    ENVIRONMENT("환경"),
    BOOK("독서"),
    EMPTY("");

    private final String title;

    public static SubjectTitle valueOfTitle(String title) {
        return Arrays.stream(SubjectTitle.values())
                .filter(SubjectTitle -> Objects.equals(title, SubjectTitle.getTitle()))
                .findFirst()
                .orElse(EMPTY);
    }

}
