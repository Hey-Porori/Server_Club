package porori.backend.domain.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import porori.backend.domain.application.model.entity.Application;
import porori.backend.domain.application.model.entity.ApplicationStatus;
import porori.backend.domain.club.model.entity.Club;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findByClubAndUserId(Club club, Long userId);

    List<Application> findByClubAndApplicationStatus(Club club, ApplicationStatus applicationStatus);

    boolean existsByClubAndUserId(Club club, Long userId);
}
