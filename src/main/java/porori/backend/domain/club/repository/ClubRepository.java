package porori.backend.domain.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import porori.backend.domain.club.model.entity.Club;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
}
