package porori.backend.domain.qualification.model.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import porori.backend.domain.club.model.entity.Club;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Qualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qualificationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QualificationDescription qualificationDescription;

    @Builder
    public Qualification(Club club, QualificationDescription qualificationDescription) {
        this.club = club;
        this.qualificationDescription = qualificationDescription;
    }
}
