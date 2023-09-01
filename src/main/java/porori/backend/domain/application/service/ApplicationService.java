package porori.backend.domain.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import porori.backend.domain.application.model.dto.ApplicationCreateRequestDTO;
import porori.backend.domain.application.model.dto.ApplicationCreateResponseDTO;
import porori.backend.domain.application.model.entity.Application;
import porori.backend.domain.application.model.entity.ApplicationStatus;
import porori.backend.domain.application.repository.ApplicationRepository;
import porori.backend.domain.club.exception.ClubException;
import porori.backend.domain.club.model.entity.Club;
import porori.backend.domain.club.repository.ClubRepository;
import porori.backend.domain.user.service.UserService;

import static porori.backend.global.common.status.ErrorStatus.INVALID_CLUB;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final UserService userService;

    private final ApplicationRepository applicationRepository;
    private final ClubRepository clubRepository;

    public ApplicationCreateResponseDTO createApplication(String token, ApplicationCreateRequestDTO applicationCreateRequestDTO) {
        Long userId = userService.getUserId(token);
        Long clubId = applicationCreateRequestDTO.getClubId();
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new ClubException(INVALID_CLUB));

        Application application = Application.builder()
                .club(club)
                .userId(userId)
                .applicationStatus(ApplicationStatus.APPLIED)
                .build();

        applicationRepository.save(application);
        return ApplicationCreateResponseDTO.from(application);
    }
}
