package porori.backend.global.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import porori.backend.domain.club.exception.ClubException;
import porori.backend.domain.club.model.entity.Club;
import porori.backend.domain.club.repository.ClubRepository;
import porori.backend.domain.member.exception.MemberException;
import porori.backend.domain.member.repository.MemberRepository;

import static porori.backend.global.common.status.ErrorStatus.*;

@Component
@RequiredArgsConstructor
public class Validator {

    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;

    public void verifyClubManager(Long clubId, Long userId) {
        if (!clubRepository.existsByClubIdAndUserId(clubId, userId))
            throw new ClubException(NOT_CLUB_MANAGER);
    }

    public void verifyClubMember(Club club, Long userId) {
        if (!memberRepository.existsByClubAndUserId(club, userId))
            throw new MemberException(NOT_EXIST_MEMBER);
    }

    public void verifyClubLimitMember(Club club) {
        if (club.getCurrentMemberNumber() >= club.getLimitMemberNumber())
            throw new MemberException(FULL_CLUB_NUMBER);
    }
}
