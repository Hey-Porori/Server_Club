package porori.backend.domain.club.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum SubjectDetail {

    /**
     * TRAVEL
     */
    CAMPING(SubjectTitle.TRAVEL, "캠핑"), WORLD(SubjectTitle.TRAVEL, "세계 여행"), DOMESTIC(SubjectTitle.TRAVEL, "국내 여행"), MOUNTAIN(SubjectTitle.TRAVEL, "등산"), CULINARY(SubjectTitle.TRAVEL, "식도락 여행"),

    /**
     * COOK
     */
    BAKING(SubjectTitle.COOK, "베이킹"), KOREA(SubjectTitle.COOK, "한식"), GLOBAL(SubjectTitle.COOK, "외국 요리"), VEGAN(SubjectTitle.COOK, "채식"),

    /**
     * LANGUAGE
     */
    SPANISH(SubjectTitle.LANGUAGE, "스페인어"), JAPANESE(SubjectTitle.LANGUAGE, "일본어"), CHINESE(SubjectTitle.LANGUAGE, "중국어"), GERMAN(SubjectTitle.LANGUAGE, "독일어"), FRENCH(SubjectTitle.LANGUAGE, "불어"),

    /**
     * SPORT
     */
    YOGA(SubjectTitle.SPORT, "요가"), HIKING(SubjectTitle.SPORT, "하이킹"), TABLE_TENNIS(SubjectTitle.SPORT, "탁구"), BADMINTON(SubjectTitle.SPORT, "배드민턴"), SWIMMING(SubjectTitle.SPORT, "수영"), MARATHON(SubjectTitle.SPORT, "마라톤"), CLIMBING(SubjectTitle.SPORT, "클라이밍"),

    /**
     * ART
     */
    DRAWING(SubjectTitle.ART, "그림"), PHOTOGRAPHY(SubjectTitle.ART, "사진"), CRAFT(SubjectTitle.ART, "공예"), SCULPTURE(SubjectTitle.ART, "조각"), FILM(SubjectTitle.ART, "영화"), DANCE(SubjectTitle.ART, "춤"),

    /**
     * SCIENCE
     */
    CODING(SubjectTitle.SCIENCE, "코딩"), ROBOT(SubjectTitle.SCIENCE, "로봇"), ASTRONOMY(SubjectTitle.SCIENCE, "우주"), ENERGY(SubjectTitle.SCIENCE, "에너지"), AI(SubjectTitle.SCIENCE, "인공지능"),

    /**
     * MUSIC
     */
    K_POP(SubjectTitle.MUSIC, "케이팝"), CLASSIC(SubjectTitle.MUSIC, "클래식"), JAZZ(SubjectTitle.MUSIC, "재즈"), POP(SubjectTitle.MUSIC, "팝"), ROCK(SubjectTitle.MUSIC, "락"), INSTRUMENT(SubjectTitle.MUSIC, "악기"), CHOIR(SubjectTitle.MUSIC, "합창"),

    /**
     * ENVIRONMENT
     */
    RECYCLING(SubjectTitle.ENVIRONMENT, "재활용"), WASTE_REDUCTION(SubjectTitle.ENVIRONMENT, "낭비 줄이기"), TREE_PLANTING(SubjectTitle.ENVIRONMENT, "나무 심기"), ECO_FRIENDLY_PRODUCT(SubjectTitle.ENVIRONMENT, "친환경 물품"),

    /**
     * BOOK
     */
    CLASSIC_LITERATURE(SubjectTitle.BOOK, "고전 소설"), MODERN_NOVEL(SubjectTitle.BOOK, "현대 소설"), PHILOSOPHY(SubjectTitle.BOOK, "철학"), SCIENCE_BOOK(SubjectTitle.BOOK, "과학"),

    EMPTY(null, "");


    private final SubjectTitle parentSubject;
    private final String detail;

    public static SubjectDetail valueOfDetail(String detail) {
        return Arrays.stream(SubjectDetail.values())
                .filter(SubjectDetail -> Objects.equals(detail, SubjectDetail.getDetail()))
                .findFirst()
                .orElse(EMPTY);
    }
}
