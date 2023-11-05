package porori.backend.domain.member.model.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import porori.backend.domain.club.model.entity.Club;
import porori.backend.global.common.entity.BaseEntity;
import porori.backend.global.common.status.BaseStatus;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    protected Member(Long userId, Club club, Role role) {
        this.userId = userId;
        this.club = club;
        this.role = role;
    }

    public void changeStatus(BaseStatus status) {
        this.status = status;
    }
}
