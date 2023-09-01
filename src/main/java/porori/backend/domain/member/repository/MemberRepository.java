package porori.backend.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import porori.backend.domain.member.model.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
