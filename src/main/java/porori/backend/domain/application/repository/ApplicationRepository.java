package porori.backend.domain.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import porori.backend.domain.application.model.entity.Application;
import porori.backend.domain.club.model.entity.Club;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findByClubAndUserId(Club club, Long userId);

    boolean existsByClubAndUserId(Club club, Long userId);
}
