package porori.backend.domain.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import porori.backend.domain.application.model.entity.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
