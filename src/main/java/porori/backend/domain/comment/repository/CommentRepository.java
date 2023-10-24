package porori.backend.domain.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import porori.backend.domain.comment.model.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
