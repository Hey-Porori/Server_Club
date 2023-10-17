package porori.backend.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import porori.backend.domain.club.model.entity.Club;
import porori.backend.domain.post.model.entity.Post;
import porori.backend.global.common.status.BaseStatus;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByClubAndStatus(Club club, BaseStatus status);
}
