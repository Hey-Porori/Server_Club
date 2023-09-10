package porori.backend.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import porori.backend.domain.club.exception.ClubException;
import porori.backend.domain.club.model.entity.Club;
import porori.backend.domain.club.repository.ClubRepository;
import porori.backend.domain.member.model.entity.Member;
import porori.backend.domain.member.model.entity.Role;
import porori.backend.domain.member.repository.MemberRepository;

import static porori.backend.global.common.status.ErrorStatus.INVALID_CLUB;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ClubRepository clubRepository;

    public void addMember(Club club, Long userId, Role role) {
        Club findClub = clubRepository.findById(club.getClubId()).orElseThrow(() -> new ClubException(INVALID_CLUB));
        findClub.increaseCurrentMemberNumber();
        clubRepository.save(findClub);

        Member member = Member.builder()
                .club(club)
                .userId(userId)
                .role(role)
                .build();

        memberRepository.save(member);
    }
}
