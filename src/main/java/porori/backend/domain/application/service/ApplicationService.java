package porori.backend.domain.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import porori.backend.domain.application.exception.ApplicationException;
import porori.backend.domain.application.model.dto.ApplicationResponseDTO;
import porori.backend.domain.application.model.entity.Application;
import porori.backend.domain.application.model.entity.ApplicationStatus;
import porori.backend.domain.application.repository.ApplicationRepository;
import porori.backend.domain.club.exception.ClubException;
import porori.backend.domain.club.model.entity.Club;
import porori.backend.domain.club.repository.ClubRepository;
import porori.backend.domain.member.model.entity.Role;
import porori.backend.domain.member.repository.MemberRepository;
import porori.backend.domain.member.service.MemberService;
import porori.backend.domain.user.service.UserService;

import static porori.backend.domain.application.model.entity.ApplicationStatus.COMPLETED;
import static porori.backend.domain.application.model.entity.ApplicationStatus.REJECTED;
import static porori.backend.global.common.status.ErrorStatus.*;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final UserService userService;
    private final MemberService memberService;

    private final ApplicationRepository applicationRepository;
    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;

    public ApplicationResponseDTO createApplication(String token, Long clubId) {
        Long userId = userService.getUserId(token);
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new ClubException(INVALID_CLUB));

        verifyAlreadyApplied(club, userId);
        verifyClubLimitMember(club);

        Application application = Application.builder()
                .club(club)
                .userId(userId)
                .applicationStatus(ApplicationStatus.APPLIED)
                .build();

        applicationRepository.save(application);
        return ApplicationResponseDTO.from(application);
    }

    private void verifyAlreadyApplied(Club club, Long userId) {
        if (applicationRepository.existsByClubAndUserId(club, userId))
            throw new ApplicationException(EXIST_APPLICATION);
    }

    private void verifyClubLimitMember(Club club) {
        if (memberRepository.findByClub(club).size() >= club.getLimitMemberNumber())
            throw new ApplicationException(FULL_CLUB_NUMBER);
    }

    public ApplicationResponseDTO acceptApplication(String token, Long clubId, Long userId) {
        Long managerId = userService.getUserId(token);
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new ClubException(INVALID_CLUB));

        verifyClubManager(clubId, managerId);

        Application application = applicationRepository.findByClubAndUserId(club, userId).orElseThrow(() -> new ApplicationException(NOT_EXIST_APPLICATION));

        application.changeStatus(COMPLETED);
        applicationRepository.save(application);

        memberService.addMember(club, userId, Role.MEMBER);

        return ApplicationResponseDTO.from(application);
    }

    public ApplicationResponseDTO rejectApplication(String token, Long clubId, Long userId) {
        Long managerId = userService.getUserId(token);
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new ClubException(INVALID_CLUB));

        verifyClubManager(clubId, managerId);

        Application application = applicationRepository.findByClubAndUserId(club, userId).orElseThrow(() -> new ApplicationException(NOT_EXIST_APPLICATION));

        application.changeStatus(REJECTED);
        applicationRepository.save(application);

        return ApplicationResponseDTO.from(application);
    }

    private void verifyClubManager(Long clubId, Long userId) {
        if (!clubRepository.existsByClubIdAndUserId(clubId, userId))
            throw new ClubException(NOT_MANAGE_CLUB);
    }

}
