package porori.backend.domain.club.model.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import porori.backend.domain.member.model.entity.Member;
import porori.backend.domain.qualification.model.entity.Qualification;
import porori.backend.global.common.entity.BaseEntity;

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
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubjectTitle subjectTitle;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubjectDetail subjectDetail;

    @OneToMany(mappedBy = "club")
    private List<Qualification> qualifications;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int limitMemberNumber;

    @OneToMany(mappedBy="club")
    private List<Member> members;

    @Builder
    protected Club(String name, SubjectTitle subjectTitle, SubjectDetail subjectDetail, List<Qualification> qualifications, String location, int limitMemberNumber) {
        this.name = name;
        this.subjectTitle = subjectTitle;
        this.subjectDetail = subjectDetail;
        this.qualifications = qualifications;
        this.location = location;
        this.limitMemberNumber = limitMemberNumber;
    }
}
