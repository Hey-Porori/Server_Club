package porori.backend.domain.club.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import porori.backend.domain.club.exception.ClubException;
import porori.backend.domain.club.model.dto.*;
import porori.backend.domain.club.model.entity.Club;
import porori.backend.domain.club.repository.ClubRepository;
import porori.backend.domain.member.model.entity.Role;
import porori.backend.domain.member.service.MemberService;
import porori.backend.domain.user.service.UserService;
import porori.backend.global.common.status.BaseStatus;

import java.util.List;
import java.util.stream.Collectors;

import static porori.backend.domain.club.model.entity.SubjectDetail.valueOfDetail;
import static porori.backend.domain.club.model.entity.SubjectTitle.EMPTY;
import static porori.backend.domain.club.model.entity.SubjectTitle.valueOfTitle;
import static porori.backend.global.common.status.ErrorStatus.*;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final UserService userService;
    private final MemberService memberService;

    private final ClubRepository clubRepository;

    public ClubCreateResponseDTO createClub(String token, ClubCreateRequestDTO clubCreateRequestDTO) {
        Long userId = userService.getUserId(token);

        Club club = Club.builder()
                .userId(userId)
                .name(clubCreateRequestDTO.getName())
                .subjectTitle(valueOfTitle(clubCreateRequestDTO.getSubjectTitle()))
                .subjectDetail(valueOfDetail(clubCreateRequestDTO.getSubjectDetail()))
                .description(clubCreateRequestDTO.getDescription())
                .location(clubCreateRequestDTO.getLocation())
                .limitMemberNumber(clubCreateRequestDTO.getLimitMemberNumber())
                .currentMemberNumber(0)
                .build();

        clubRepository.save(club);
        memberService.addMember(club, userId, Role.MANAGER);
        return ClubCreateResponseDTO.from(club);
    }

    public List<ClubGetResponseDTO> getAllClubs() {
        List<Club> clubs = clubRepository.findByStatus(BaseStatus.ACTIVE);

        return clubs.stream()
                .map(ClubGetResponseDTO::from)
                .collect(Collectors.toList());
    }

    public List<ClubGetResponseDTO> getSubjectClubs(String title) {
        verifySubjectTitle(title);

        List<Club> clubs = clubRepository.findBySubjectTitleAndStatus(valueOfTitle(title), BaseStatus.ACTIVE);

        return clubs.stream()
                .map(ClubGetResponseDTO::from)
                .collect(Collectors.toList());
    }

    private void verifySubjectTitle(String title) {
        if (valueOfTitle(title).equals(EMPTY))
            throw new ClubException(INVALID_SUBJECT_TITLE);
    }

    public ClubGetResponseDTO updateClub(String token, ClubUpdateRequestDTO clubUpdateRequestDTO) {
        Long userId = userService.getUserId(token);
        Long clubId = clubUpdateRequestDTO.getClubId();

        Club findClub = clubRepository.findById(clubId).orElseThrow(() -> new ClubException(INVALID_CLUB));
        verifyClubManager(clubId, userId);
        verifyCurrentClubMemberNumber(clubUpdateRequestDTO.getLimitMemberNumber(), findClub.getCurrentMemberNumber());

        Club club = Club.builder()
                .clubId(clubId)
                .userId(userId)
                .name(clubUpdateRequestDTO.getName())
                .subjectTitle(valueOfTitle(clubUpdateRequestDTO.getSubjectTitle()))
                .subjectDetail(valueOfDetail(clubUpdateRequestDTO.getSubjectDetail()))
                .description(clubUpdateRequestDTO.getDescription())
                .location(clubUpdateRequestDTO.getLocation())
                .limitMemberNumber(clubUpdateRequestDTO.getLimitMemberNumber())
                .currentMemberNumber(findClub.getCurrentMemberNumber())
                .build();

        clubRepository.save(club);
        return ClubGetResponseDTO.from(club);
    }

    private void verifyCurrentClubMemberNumber(int limitMemberNumber, int currentMemberNumber) {
        if (limitMemberNumber <= currentMemberNumber)
            throw new ClubException(FULL_CLUB_NUMBER);
    }

    public ClubDeleteResponseDTO deleteClub(String token, Long clubId) {
        Long userId = userService.getUserId(token);

        Club findClub = clubRepository.findById(clubId).orElseThrow(() -> new ClubException(INVALID_CLUB));
        verifyClubManager(clubId, userId);

        findClub.changeStatus(BaseStatus.INACTIVE);
        clubRepository.save(findClub);
        return ClubDeleteResponseDTO.from(findClub);
    }

    private void verifyClubManager(Long clubId, Long userId) {
        if (!clubRepository.existsByClubIdAndUserId(clubId, userId))
            throw new ClubException(NOT_MANAGE_CLUB);
    }
}
