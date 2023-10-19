package porori.backend.domain.post.model.entity;

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
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean isImportant;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Subject subject;

    @Builder
    protected Post(Long postId, Long userId, Club club, String title, String content, boolean isImportant, Subject subject) {
        this.postId = postId;
        this.userId = userId;
        this.club = club;
        this.title = title;
        this.content = content;
        this.isImportant = isImportant;
        this.subject = subject;
    }

    public void changeStatus(BaseStatus status) {
        this.status = status;
    }

}
