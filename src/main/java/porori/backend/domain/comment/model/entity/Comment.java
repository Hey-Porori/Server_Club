package porori.backend.domain.comment.model.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import porori.backend.domain.post.model.entity.Post;
import porori.backend.global.common.entity.BaseEntity;
import porori.backend.global.common.status.BaseStatus;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(nullable = false)
    private String content;

    @Builder
    protected Comment(Long commentId, Long userId, Post post, String content) {
        this.commentId = commentId;
        this.userId = userId;
        this.post = post;
        this.content = content;
    }

    public void changeStatus(BaseStatus status) {
        this.status = status;
    }
}
