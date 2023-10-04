package porori.backend.domain.club.model.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import porori.backend.domain.member.model.entity.Member;
import porori.backend.global.common.entity.BaseEntity;
import porori.backend.global.common.status.BaseStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Club extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clubId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubjectTitle subjectTitle;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubjectDetail subjectDetail;

    @Column
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int limitMemberNumber;

    @Column(nullable = false)
    private int currentMemberNumber;

    @OneToMany(mappedBy="club")
    private List<Member> members;

    @Builder
    protected Club(Long clubId, Long userId, String name, SubjectTitle subjectTitle, SubjectDetail subjectDetail, String description, String location, int limitMemberNumber, int currentMemberNumber) {
        this.clubId = clubId;
        this.userId = userId;
        this.name = name;
        this.subjectTitle = subjectTitle;
        this.subjectDetail = subjectDetail;
        this.description = description;
        this.location = location;
        this.limitMemberNumber = limitMemberNumber;
        this.currentMemberNumber = currentMemberNumber;
    }

    public void increaseCurrentMemberNumber() {
        this.currentMemberNumber = this.currentMemberNumber + 1;
    }

    public void decreaseCurrentMemberNumber() {
        this.currentMemberNumber = this.currentMemberNumber - 1;
    }

    public void changeStatus(BaseStatus status) {
        this.status = status;
    }
}
