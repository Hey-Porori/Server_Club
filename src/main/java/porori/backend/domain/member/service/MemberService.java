package porori.backend.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import porori.backend.domain.application.exception.ApplicationException;
import porori.backend.domain.club.exception.ClubException;
import porori.backend.domain.club.model.entity.Club;
import porori.backend.domain.club.repository.ClubRepository;
import porori.backend.domain.member.exception.MemberException;
import porori.backend.domain.member.model.dto.MemberResponseDTO;
import porori.backend.domain.member.model.dto.MemberStatusResponseDTO;
import porori.backend.domain.member.model.entity.Member;
import porori.backend.domain.member.model.entity.Role;
import porori.backend.domain.member.repository.MemberRepository;
import porori.backend.domain.user.service.UserService;
import porori.backend.global.common.status.BaseStatus;
import porori.backend.global.utils.Validator;

import java.util.List;
import java.util.stream.Collectors;

import static porori.backend.global.common.status.BaseStatus.ACTIVE;
import static porori.backend.global.common.status.BaseStatus.INACTIVE;
import static porori.backend.global.common.status.ErrorStatus.*;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final UserService userService;

    private final MemberRepository memberRepository;
    private final ClubRepository clubRepository;

    private final Validator validator;

    public void addMember(Club club, Long userId, Role role) {
        Club findClub = clubRepository.findById(club.getClubId()).orElseThrow(() -> new ClubException(INVALID_CLUB));
        verifyClubLimitMember(findClub);
        findClub.increaseCurrentMemberNumber();
        clubRepository.save(findClub);

        Member member = Member.builder()
                .club(club)
                .userId(userId)
                .role(role)
                .build();

        memberRepository.save(member);
    }

    private void verifyClubLimitMember(Club club) {
        if (club.getCurrentMemberNumber() >= club.getLimitMemberNumber())
            throw new ApplicationException(FULL_CLUB_NUMBER);
    }

    public List<MemberResponseDTO> getMemberList(String token, Long clubId) {
        Long managerId = userService.getUserId(token);
        validator.verifyClubManager(clubId, managerId);
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new ClubException(INVALID_CLUB));

        List<Member> members = memberRepository.findByClubAndStatus(club, ACTIVE);
        return userService.getMemberResponseDTO(members.stream().map(Member::getUserId).collect(Collectors.toList()));
    }

    public MemberStatusResponseDTO kickOutMember(String token, Long clubId, Long userId) {
        Long managerId = userService.getUserId(token);
        validator.verifyClubManager(clubId, managerId);
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new ClubException(INVALID_CLUB));

        Member member = memberRepository.findByClubAndUserId(club, userId).orElseThrow(() -> new MemberException(NOT_EXIST_MEMBER));

        member.changeStatus(BaseStatus.INACTIVE);
        memberRepository.save(member);

        club.decreaseCurrentMemberNumber();
        clubRepository.save(club);
        return MemberStatusResponseDTO.from(member);
    }

    public MemberStatusResponseDTO quitMember(String token, Long clubId) {
        Long userId = userService.getUserId(token);
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new ClubException(INVALID_CLUB));

        Member member = memberRepository.findByClubAndUserId(club, userId).orElseThrow(() -> new MemberException(NOT_EXIST_MEMBER));
        verifyQuitManager(member);

        member.changeStatus(INACTIVE);
        memberRepository.save(member);

        club.decreaseCurrentMemberNumber();
        clubRepository.save(club);
        return MemberStatusResponseDTO.from(member);
    }

    private void verifyQuitManager(Member member) {
        if (member.getRole().equals(Role.MANAGER))
            throw new ClubException(MANAGER_CANT_QUIT);
    }
}
