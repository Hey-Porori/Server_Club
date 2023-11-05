package porori.backend.domain.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import porori.backend.domain.comment.model.entity.Comment;
import porori.backend.domain.post.model.entity.Post;
import porori.backend.global.common.status.BaseStatus;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostAndStatus(Post post, BaseStatus status);

    boolean existsByCommentIdAndUserId(Long commentId, Long userId);
}
