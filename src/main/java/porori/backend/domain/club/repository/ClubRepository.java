package porori.backend.domain.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import porori.backend.domain.club.model.entity.Club;
import porori.backend.domain.club.model.entity.SubjectTitle;
import porori.backend.global.common.status.BaseStatus;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    List<Club> findByStatus(BaseStatus status);

    List<Club> findBySubjectTitleAndStatus(SubjectTitle subjectTitle, BaseStatus status);

    boolean existsByClubIdAndUserId(Long clubId, Long userId);
}
