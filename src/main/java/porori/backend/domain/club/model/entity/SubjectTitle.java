package porori.backend.domain.club.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SubjectTitle {
    TRAVEL(0),
    COOK(1),
    LANGUAGE(2),
    SPORT(3),
    ART(4),
    SCIENCE(5),
    MUSIC(6),
    ENVIRONMENT(7),
    BOOK(8);

    private final int index;

}
