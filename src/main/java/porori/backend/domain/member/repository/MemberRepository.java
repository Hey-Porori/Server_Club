package porori.backend.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import porori.backend.domain.club.model.entity.Club;
import porori.backend.domain.member.model.entity.Member;
import porori.backend.global.common.status.BaseStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByClubAndStatus(Club club, BaseStatus status);

    Optional<Member> findByClubAndUserId(Club club, Long userId);
}
