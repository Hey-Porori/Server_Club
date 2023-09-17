package porori.backend.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import porori.backend.domain.club.exception.ClubException;
import porori.backend.domain.club.model.entity.Club;
import porori.backend.domain.club.repository.ClubRepository;
import porori.backend.domain.member.model.dto.MemberResponseDTO;
import porori.backend.domain.member.model.entity.Member;
import porori.backend.domain.member.model.entity.Role;
import porori.backend.domain.member.repository.MemberRepository;
import porori.backend.domain.user.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import static porori.backend.global.common.status.ErrorStatus.INVALID_CLUB;
import static porori.backend.global.common.status.ErrorStatus.NOT_MANAGE_CLUB;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final UserService userService;

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

    public List<MemberResponseDTO> getMemberList(String token, Long clubId) {
        Long managerId = userService.getUserId(token);
        Club club = verifyClubManager(clubId, managerId);

        List<Member> members = memberRepository.findByClub(club);
        return members.stream()
                .map(MemberResponseDTO::from)
                .collect(Collectors.toList());
    }

    private Club verifyClubManager(Long clubId, Long userId) {
        if (!clubRepository.existsByClubIdAndUserId(clubId, userId))
            throw new ClubException(NOT_MANAGE_CLUB);
        return clubRepository.findById(clubId).orElseThrow(() -> new ClubException(INVALID_CLUB));
    }
}
