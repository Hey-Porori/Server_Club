package porori.backend.domain.application.model.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import porori.backend.domain.club.model.entity.Club;
import porori.backend.global.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Application extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus applicationStatus;

    @Builder
    protected Application(Long applicationId, Club club, Long userId, ApplicationStatus applicationStatus) {
        this.applicationId = applicationId;
        this.club = club;
        this.userId = userId;
        this.applicationStatus = applicationStatus;
    }
}
