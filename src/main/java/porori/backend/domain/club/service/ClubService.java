package porori.backend.domain.club.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import porori.backend.domain.club.model.dto.ClubCreateRequestDTO;
import porori.backend.domain.club.model.dto.ClubCreateResponseDTO;
import porori.backend.domain.club.model.entity.Club;
import porori.backend.domain.club.repository.ClubRepository;
import porori.backend.domain.member.model.entity.Role;
import porori.backend.domain.member.service.MemberService;
import porori.backend.domain.user.service.UserService;

import static porori.backend.domain.club.model.entity.SubjectDetail.valueOfDetail;
import static porori.backend.domain.club.model.entity.SubjectTitle.valueOfTitle;

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
                .build();

        clubRepository.save(club);
        memberService.addMember(club, userId, Role.MANAGER);
        return ClubCreateResponseDTO.from(club);
    }
}
