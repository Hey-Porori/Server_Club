package porori.backend.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import porori.backend.domain.post.model.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
