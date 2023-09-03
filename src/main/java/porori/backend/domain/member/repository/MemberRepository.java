package porori.backend.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import porori.backend.domain.club.model.entity.Club;
import porori.backend.domain.member.model.entity.Member;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByClub(Club club);
}
