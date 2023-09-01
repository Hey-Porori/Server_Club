package porori.backend.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import porori.backend.domain.club.model.entity.Club;
import porori.backend.domain.member.model.entity.Member;
import porori.backend.domain.member.model.entity.Role;
import porori.backend.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void addMember(Club club, Long userId, Role role) {
        Member member = Member.builder()
                .club(club)
                .userId(userId)
                .role(role)
                .build();

        memberRepository.save(member);
    }
}
