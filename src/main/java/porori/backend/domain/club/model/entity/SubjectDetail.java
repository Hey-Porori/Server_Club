package porori.backend.domain.club.model.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SubjectDetail {

    /**
     * TRAVEL
     */
    CAMPING(SubjectTitle.TRAVEL), WORLD(SubjectTitle.TRAVEL), DOMESTIC(SubjectTitle.TRAVEL), MOUNTAIN(SubjectTitle.TRAVEL), CULINARY(SubjectTitle.TRAVEL),

    /**
     * COOK
     */
    BAKING(SubjectTitle.COOK), KOREA(SubjectTitle.COOK), GLOBAL(SubjectTitle.COOK), VEGAN(SubjectTitle.COOK),

    /**
     * LANGUAGE
     */
    SPANISH(SubjectTitle.LANGUAGE), JAPANESE(SubjectTitle.LANGUAGE), CHINESE(SubjectTitle.LANGUAGE), GERMAN(SubjectTitle.LANGUAGE), FRENCH(SubjectTitle.LANGUAGE),

    /**
     * SPORT
     */
    YOGA(SubjectTitle.SPORT), HIKING(SubjectTitle.SPORT), TABLE_TENNIS(SubjectTitle.SPORT), BADMINTON(SubjectTitle.SPORT), SWIMMING(SubjectTitle.SPORT), MARATHON(SubjectTitle.SPORT), CLIMBING(SubjectTitle.SPORT),

    /**
     * ART
     */
    DRAWING(SubjectTitle.ART), PHOTOGRAPHY(SubjectTitle.ART), CRAFT(SubjectTitle.ART), SCULPTURE(SubjectTitle.ART), FILM(SubjectTitle.ART), DANCE(SubjectTitle.ART),

    /**
     * SCIENCE
     */
    CODING(SubjectTitle.SCIENCE), ROBOT(SubjectTitle.SCIENCE), ASTRONOMY(SubjectTitle.SCIENCE), ENERGY(SubjectTitle.SCIENCE), AI(SubjectTitle.SCIENCE),

    /**
     * MUSIC
     */
    K_POP(SubjectTitle.MUSIC), CLASSIC(SubjectTitle.MUSIC), JAZZ(SubjectTitle.MUSIC), POP(SubjectTitle.MUSIC), ROCK(SubjectTitle.MUSIC), INSTRUMENT(SubjectTitle.MUSIC), CHOIR(SubjectTitle.MUSIC),

    /**
     * ENVIRONMENT
     */
    RECYCLING(SubjectTitle.ENVIRONMENT), WASTE_REDUCTION(SubjectTitle.ENVIRONMENT), TREE_PLANTING(SubjectTitle.ENVIRONMENT), ECO_FRIENDLY_PRODUCT(SubjectTitle.ENVIRONMENT),

    /**
     * BOOK
     */
    CLASSIC_LITERATURE(SubjectTitle.BOOK), MODERN_NOVEL(SubjectTitle.BOOK), PHILOSOPHY(SubjectTitle.BOOK), SCIENCE_BOOK(SubjectTitle.BOOK);


    private final SubjectTitle parentSubject;

}
